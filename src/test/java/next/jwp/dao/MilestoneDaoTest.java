package next.jwp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import next.jwp.FinalProjectApplication;
import next.jwp.model.Milestone;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(FinalProjectApplication.class)
public class MilestoneDaoTest {
    @Autowired
    private MilestoneDao milestoneDao;

    @Test
    public void insertTest() {
        Milestone milestone = new Milestone("subject");
        Milestone expected = milestoneDao.insert(milestone);

        Milestone findmilestone = milestoneDao.findById(expected.getId());
        assertNotNull(findmilestone);
    }

    @Test
    public void deleteTest() {
        Milestone milestone = new Milestone("subject");
        milestone = milestoneDao.insert(milestone);
        milestoneDao.delete(milestone.getId());

        Milestone findmilestone = milestoneDao.findById(milestone.getId());
        assertNull(findmilestone);
    }

    @Test
    public void updateTest() {
        Milestone milestone = new Milestone("subject");
        milestone = milestoneDao.insert(milestone);

        milestone.setSubject("subject2");
        milestoneDao.update(milestone);

        Milestone findmilestone = milestoneDao.findById(milestone.getId());
        assertEquals(findmilestone.getSubject(), "subject2");
    }

}
