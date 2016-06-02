package next.jwp.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import next.jwp.dao.UserDao;
import next.jwp.model.User;
import next.jwp.util.FileUploadUtil;

@Controller
public class UserController {

    @Autowired
    UserDao userDao;
    @Autowired
    FileUploadUtil fileUploadUtil;
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String getjoinForm() {
        return "join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(@RequestParam("userId") String userId, @RequestParam("name") String name,
            @RequestParam("password") String password, @RequestParam("pic") MultipartFile pic, Model model) {
        
        if(userDao.findByUserId(userId) != null){
            model.addAttribute("message", "중복 아이디가 존재합니다");
            return "join";
        }
        User user = new User(userId, name, password);
        if(pic != null){
            user.setPic(fileUploadUtil.saveFile(pic, servletContext.getRealPath("/"), "userpic"));
        }
        userDao.insert(user);
        
        return "redirect:/";
    }

}
