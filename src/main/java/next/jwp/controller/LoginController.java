package next.jwp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import next.jwp.dao.UserDao;
import next.jwp.model.User;

@Controller
public class LoginController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, @RequestParam("userId") String userId,
            @RequestParam("password") String password, Model model) {
        User user = userDao.findByUserId(userId);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("message", "아이디와 비밀번호를 다시 확인해주세요");
            return "login";
        }

        session.setAttribute("user", user);
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
