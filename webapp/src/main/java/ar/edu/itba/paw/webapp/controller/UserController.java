package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService us;

    @RequestMapping("/user")
    public ModelAndView index(@RequestParam(value = "userId", required = true) final Integer userId) {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", us.findUserById(userId).getUsername());
        return mav;
    }

    @RequestMapping("/create")
    public ModelAndView create(@RequestParam(value = "username", required = true) final String username) {
        final User u = us.createUser(username, "asd", "asd", "asd", 1245, LocalDate.parse("2000-11-09"));
        return new ModelAndView("redirect:/?username=" + u.getUsername());
    }
}
