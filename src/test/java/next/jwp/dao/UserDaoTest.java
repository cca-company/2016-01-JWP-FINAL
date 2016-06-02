package next.jwp.dao;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import next.jwp.FinalProjectApplication;
import next.jwp.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(FinalProjectApplication.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    
    @Test
    public void insertTest() {
        User user = new User("userId","name","password");
        User expected = userDao.insert(user);
        
        User findUser = userDao.findById(expected.getId());
        assertEquals(expected, findUser);
    }

    @Test
    public void deleteTest() {
        User user = new User("userId","name","password");
        user = userDao.insert(user);
        userDao.delete(user.getId());
        
        User findUser = userDao.findById(user.getId());
        assertNull(findUser);
    }

    @Test
    public void updateTest() {
        User user = new User("userId","name","password");
        user = userDao.insert(user);
        
        user.setName("name1");
        user.setUserId("userId1");
        user.setPassword("password1");
        userDao.update(user);
        
        User findUser = userDao.findById(user.getId());
        assertEquals(findUser.getName(), "name1");
        assertEquals(findUser.getUserId(), "userId1");
        assertEquals(findUser.getPassword(), "password1");
    }


}
