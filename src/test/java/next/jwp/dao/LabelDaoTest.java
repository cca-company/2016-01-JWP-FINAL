package next.jwp.dao;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import next.jwp.FinalProjectApplication;
import next.jwp.model.Label;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(FinalProjectApplication.class)
public class LabelDaoTest {
    @Autowired
    private LabelDao labelDao;
    
    @Test
    public void insertTest() {
        Label label = new Label("lebelText","color");
        Label expected = labelDao.insert(label);
        
        Label findlabel = labelDao.findById(expected.getId());
        assertEquals(expected, findlabel);
    }

    @Test
    public void deleteTest() {
        Label label = new Label("labelText","color");
        label = labelDao.insert(label);
        labelDao.delete(label.getId());
        
        Label findlabel = labelDao.findById(label.getId());
        assertNull(findlabel);
    }

    @Test
    public void updateTest() {
        Label label = new Label("labelText","color");
        label = labelDao.insert(label);
        
        label.setLabelText("labelText1");
        label.setColor("color1");
        labelDao.update(label);
        
        Label findlabel = labelDao.findById(label.getId());
        assertEquals(findlabel.getLabelText(), "labelText1");
        assertEquals(findlabel.getColor(), "color1");
    }


}
