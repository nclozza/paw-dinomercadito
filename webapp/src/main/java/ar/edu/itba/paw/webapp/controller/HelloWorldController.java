package ar.edu.itba.paw.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloWorldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/index")
    public ModelAndView helloWorld() {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("greeting", "PAW");
        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
