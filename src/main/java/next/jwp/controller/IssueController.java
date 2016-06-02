package next.jwp.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import next.jwp.dao.HistoryDao;
import next.jwp.dao.IssueDao;
import next.jwp.dao.LabelDao;
import next.jwp.dao.MilestoneDao;
import next.jwp.dao.UserDao;
import next.jwp.model.History;
import next.jwp.model.Issue;
import next.jwp.model.Milestone;
import next.jwp.model.User;
import next.jwp.util.FileUploadUtil;

@Controller
public class IssueController {
    @Autowired
    IssueDao issueDao;
    @Autowired
    HistoryDao historyDao;
    @Autowired
    UserDao userDao;
    @Autowired
    MilestoneDao milestoneDao;
    @Autowired
    LabelDao labelDao;
    @Autowired
    FileUploadUtil fileUtil;
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/issue/{id}", method = RequestMethod.GET)
    public String getIssue(@PathVariable("id") int id, Model model) {
        Issue issue = issueDao.findById(id);
        model.addAttribute("issue", issue);
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("writer", userDao.findById(issue.getWriterId()));
        model.addAttribute("milestones", milestoneDao.findAll());
        model.addAttribute("labels", labelDao.findAll());
        model.addAttribute("histories", getHistories(id));
        return "issue";
    }

    @RequestMapping(value = "/newIssue", method = RequestMethod.GET)
    public String getIssueForm(HttpSession session) {
        if(session.getAttribute("user") == null){
            return "redirect:/";
        }
        return "form";
    }

    @RequestMapping(value = "/newIssue", method = RequestMethod.POST)
    public String addIssue(HttpSession session, @RequestParam("subject") String subject, @RequestParam("comment") String comment) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        Issue newIssue = new Issue(user.getId(), 0, subject, comment);
        newIssue = issueDao.insert(newIssue);
        return "redirect:/issue/" + newIssue.getId();
    }

    @RequestMapping(value = "/issue/{id}/setOpen", method = RequestMethod.GET)
    public String setOpen(HttpSession session, @PathVariable("id") int id) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/issue/"+id;
        }
        
        Issue issue = issueDao.findById(id);
        issue.setIsOpen(!issue.getIsOpen());
        String status = "";
        if (issue.getIsOpen()) {
            status = "reopen";
        } else {
            status = "close";
        }
        History history = new History(id, user.getId(), "changed", status + " issue");
        historyDao.insert(history);
        issueDao.update(issue);
        return "redirect:/issue/" + id;
    }

    @RequestMapping(value = "/issue/{issueId}/setMilestone/{milestoneId}", method = RequestMethod.GET)
    public String setMilestone(HttpSession session, @PathVariable("issueId") int issueId, @PathVariable("milestoneId") int milestoneId) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/issue/"+issueId;
        }
        Issue issue = issueDao.findById(issueId);
        issue.setMilestoneId(milestoneId);
        issueDao.update(issue);
        Milestone milestone = milestoneDao.findById(milestoneId);
        if (milestone != null) {
            History history = new History(issueId, user.getId(), "changed", "chage milestone " + milestone.getSubject());
            historyDao.insert(history);
        }
        return "redirect:/issue/" + issueId;
    }

    @RequestMapping(value = "/issue/{issueId}/setLabel/{labelId}", method = RequestMethod.GET)
    public String setLabel(HttpSession session, @PathVariable("issueId") int issueId, @PathVariable("labelId") int labelId) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/issue/"+issueId;
        }
        Issue issue = issueDao.findById(issueId);
        issue.setLabelId(labelId);
        issueDao.update(issue);
        History history = new History(issueId, user.getId(), "changed", "chage label");
        historyDao.insert(history);
        return "redirect:/issue/" + issueId;
    }

    @RequestMapping(value = "/issue/{issueId}/setAssignee/{assigneeId}", method = RequestMethod.GET)
    public String setAssignee(HttpSession session, @PathVariable("issueId") int issueId, @PathVariable("assigneeId") int assigneeId) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/issue/"+issueId;
        }
        Issue issue = issueDao.findById(issueId);
        issue.setAssigneeId(assigneeId);
        issueDao.update(issue);
        User assignee = userDao.findById(assigneeId);
        if (assignee != null) {
            History history = new History(issueId, user.getId(), "changed", "set assignee " + assignee.getName());
            historyDao.insert(history);
        }
        return "redirect:/issue/" + issueId;
    }

    @RequestMapping(value = "/issue/{issueId}/addComment", method = RequestMethod.POST)
    public String addComment(HttpSession session, @PathVariable("issueId") int issueId, @RequestParam("comment") String comment) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/issue/"+issueId;
        }
        History history = new History(issueId, user.getId(), "comment", comment);
        historyDao.insert(history);
        return "redirect:/issue/" + issueId;
    }

    @RequestMapping(value = "/issue/{issueId}/uploadFile", method = RequestMethod.POST)
    public String uploadFile(HttpSession session, @PathVariable("issueId") int issueId, @RequestParam("file") MultipartFile file) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/issue/"+issueId;
        }
        History history = new History(issueId, user.getId(), "file", fileUtil.saveFile(file, servletContext.getRealPath("/"), ""));
        historyDao.insert(history);
        return "redirect:/issue/" + issueId;
    }

    @RequestMapping(value = "/updateIssue/{id}", method = RequestMethod.GET)
    private String getUpdateForm(HttpSession session, @PathVariable("id") int id, Model model) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/issue/"+id;
        }
        Issue issue = issueDao.findById(id);
        model.addAttribute(issue);
        return "updateForm";
    }

    @RequestMapping(value = "/updateIssue/{id}", method = RequestMethod.POST)
    public String updateIssue(@PathVariable("id") int id, @RequestParam("subject") String subject,
            @RequestParam("comment") String comment) {
        Issue issue = issueDao.findById(id);
        issue.setSubject(subject);
        issue.setComment(comment);
        issueDao.update(issue);
        return "redirect:/issue/" + id;
    }

    @RequestMapping(value = "/deleteIssue/{id}", method = RequestMethod.GET)
    private String deleteIssue(HttpSession session, @PathVariable("id") int id) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/issue/"+id;
        }
        issueDao.delete(id);
        for (History history : historyDao.findAllByIssueId(id)) {
            historyDao.delete(history.getId());
        }
        return "redirect:/";
    }

    private List<History> getHistories(int issueId) {
        List<History> histories = historyDao.findAllByIssueId(issueId);
        for (History history : histories) {
            User writer = userDao.findById(history.getWriterId());
            history.setWriterName(writer.getName());
            history.setWriterPic(writer.getPic());
        }
        return histories;
    }

}
