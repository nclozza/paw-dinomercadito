package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;

import java.util.Date;

@Controller
public class HelloWorldController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService us;

    @RequestMapping("/")
    public ModelAndView helloWorld() {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("greeting", "PAW");
        return mav;
    }

    @RequestMapping("/user")
    public ModelAndView index(@RequestParam(value = "userId", required = true) final String username) {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", us.findUserByUsername(username));
        return mav;
    }

    @RequestMapping("/create")
    public ModelAndView create(@RequestParam(value = "name", required = true) final String username) {
        final User u = us.createUser(username, "asd", "asd", "asd", "asd", new Date(123456));
        return new ModelAndView("redirect:/?username=" + u.getUsername());
    }
}
