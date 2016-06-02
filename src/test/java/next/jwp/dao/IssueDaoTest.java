package next.jwp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import next.jwp.FinalProjectApplication;
import next.jwp.model.Issue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(FinalProjectApplication.class)
public class IssueDaoTest {
    @Autowired
    private IssueDao issueDao;
    
    @Test
    public void insertTest() {
        Issue issue = new Issue(1, 1, "subject", "comment");
        Issue expected = issueDao.insert(issue);
        
        Issue findIssue = issueDao.findById(expected.getId());
        assertEquals(expected, findIssue);
    }
  
    @Test
    public void deleteTest() {
        Issue issue = new Issue(1, 1, "subject", "comment");
        issue = issueDao.insert(issue);
        issueDao.delete(issue.getId());
        
        Issue findIssue = issueDao.findById(issue.getId());
        assertNull(findIssue);
    }

    @Test
    public void updateTest() {
        Issue issue = new Issue(1, 1, "subject", "comment");
        issue = issueDao.insert(issue);
        
        issue.setMilestoneId(2);
        issue.setSubject("subject1");
        issue.setComment("comment2");
        issueDao.update(issue);

        Issue findIssue = issueDao.findById(issue.getId());
        assertEquals(findIssue.getMilestoneId(), 2);
        assertEquals(findIssue.getSubject(), "subject1");
        assertEquals(findIssue.getComment(), "comment2");
    }


}
