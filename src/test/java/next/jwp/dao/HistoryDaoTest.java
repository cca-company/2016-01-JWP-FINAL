package next.jwp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import next.jwp.FinalProjectApplication;
import next.jwp.model.History;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(FinalProjectApplication.class)
public class HistoryDaoTest {
    @Autowired
    private HistoryDao historyDao;
    
    @Test
    public void insertTest() {
        History history = new History(1, 1,"type", "content");
        History expected = historyDao.insert(history);
        
        History findhistory = historyDao.findById(expected.getId());
        assertEquals(expected, findhistory);
    }
  
    @Test
    public void deleteTest() {
        History history = new History(1, 1, "type", "content");
        history = historyDao.insert(history);
        historyDao.delete(history.getId());
        
        History findhistory = historyDao.findById(history.getId());
        assertNull(findhistory);
    }

    @Test
    public void updateTest() {
        History history = new History(1, 1, "type", "content");
        history = historyDao.insert(history);
        
        history.setContent("content2");
        historyDao.update(history);

        History findhistory = historyDao.findById(history.getId());
        assertEquals(findhistory.getContent(), "content2");
    }


}
