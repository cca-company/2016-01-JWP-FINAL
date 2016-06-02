package next.jwp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import next.jwp.dao.HistoryDao;
import next.jwp.dao.IssueDao;
import next.jwp.dao.UserDao;
import next.jwp.model.History;
import next.jwp.model.Issue;
import next.jwp.model.User;

@Controller
public class IndexController {
    @Autowired
    IssueDao issueDao;
    @Autowired
    UserDao userDao;
    @Autowired
    HistoryDao historyDao;

    @RequestMapping(value = "/")
    public String getIndex(Model model) {
        model.addAttribute("issues", getIssues());
        return "index";
    }

    private List<Issue> getIssues() {
        List<Issue> issues = issueDao.findAll();
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
