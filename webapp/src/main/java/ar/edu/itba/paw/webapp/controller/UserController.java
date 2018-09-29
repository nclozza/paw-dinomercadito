package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.EmailService;
import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.form.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService us;

    @Autowired
    private EmailService emailService;

    @RequestMapping("/user")
    public ModelAndView index(@RequestParam(value = "userId", required = true) final Integer userId, final HttpSession session) {
        final ModelAndView mav = new ModelAndView("user");
        User user = us.findUserByUserId(userId);
        mav.addObject("username", user.getUsername());
        mav.addObject("userId", user.getUserId());
        return mav;
    }

    @RequestMapping(value = "/login", method={RequestMethod.GET})
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/signUp", method = {RequestMethod.GET})
    public ModelAndView signUp(@ModelAttribute("registerForm") final UserForm form) {
        return new ModelAndView("signUp");
    }

    @RequestMapping(value = "/signUp", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("registerForm") final UserForm form, final BindingResult errors) {
        if (errors.hasErrors() || !form.checkPassword()) {
            return signUp(form);
        }

        final User user = us.createUser(form.getUsername(), form.getPassword(), form.getEmail(), form.getPhone(),
                form.getBirthdate());
        emailService.sendSuccessfulRegistrationEmail(user.getEmail(), user.getUsername());

        return new ModelAndView("redirect:/user?userId=" + user.getUserId());
    }

    @ModelAttribute("userId")
    public Integer loggedUser(final HttpSession session)
    {
        return (Integer) session.getAttribute("userid");
    }
}
