package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService us;

    @RequestMapping("/user")
    public ModelAndView index(@RequestParam(value = "userId", required = true) final Integer userId) {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", us.findUserByUserId(userId).getUsername());
        return mav;
    }

    @RequestMapping("/create")
    public ModelAndView create(@RequestParam(value = "username", required = true) final String username) {
        final User u = us.createUser(username, "123", "mail", "123435", "2000-11-09");

        return new ModelAndView("redirect:/?username=" + u.getUsername());
    }
}
