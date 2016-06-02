package next.jwp.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import next.jwp.dao.HistoryDao;
import next.jwp.dao.IssueDao;
import next.jwp.dao.MilestoneDao;
import next.jwp.dao.UserDao;
import next.jwp.model.History;
import next.jwp.model.Issue;
import next.jwp.model.Milestone;
import next.jwp.model.User;

@Controller
public class MilestoneController {

    @Autowired
    MilestoneDao milestoneDao;

    @Autowired
    IssueDao issueDao;
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    HistoryDao historyDao;

    @RequestMapping(value = "/milestones")
    public String getMilestones(Model model) {
        List<Milestone> milestones = milestoneDao.findAll();
        for(Milestone milestone : milestones){
            int issuesNum = 0;
            int openIssuesNum = 0;
            for(Issue issue : issueDao.findAllByMilestoneId(milestone.getId())){
                if(issue.getIsOpen()){
                    openIssuesNum++;
                }
                issuesNum++;
            }
            milestone.setOpenIssuesNum(openIssuesNum);
            milestone.setIssuesNum(issuesNum);
        }
        model.addAttribute("milestones", milestones);
        return "milestones";
    }

    @RequestMapping(value = "/milestone/{id}", method = RequestMethod.GET)
    public String getMilestones(@PathVariable("id") int id, Model model) {
        model.addAttribute("issues", getIssues(id));
        return "index";
    }

    @RequestMapping(value = "/newMilestone", method = RequestMethod.GET)
    public String getMilestoneForm(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/milestone";
        }
        return "milestoneForm";
    }

    @RequestMapping(value = "/newMilestone", method = RequestMethod.POST)
    public String addMilestone(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
            @RequestParam("subject") String subject, Model model) throws ParseException {
        Milestone newMilestone = new Milestone(subject);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        newMilestone.setStartDate(new Date(dateFormat.parse(startDate).getTime()));
        newMilestone.setEndDate(new Date(dateFormat.parse(endDate).getTime()));
        newMilestone = milestoneDao.insert(newMilestone);
        return "redirect:/milestone"+newMilestone.getId();
    }


    private List<Issue> getIssues(int milestoneId) {
        List<Issue> issues = issueDao.findAllByMilestoneId(milestoneId);
        for (Issue issue : issues) {
            User writer = userDao.findById(issue.getWriterId());
            issue.setWriterName(writer.getName());
            issue.setWriterPic(writer.getPic());
            List<History> histories = historyDao.findAllByIssueId(issue.getId());
            issue.setCommentNum(getCommentNum(histories));
        }
        return issues;
    }

    private int getCommentNum(List<History> histories) {
        int commentNum = 0;
        for (History history : histories) {
            if (history.getType().equals("comment"))
                commentNum++;
        }
        return commentNum;
    }
}
