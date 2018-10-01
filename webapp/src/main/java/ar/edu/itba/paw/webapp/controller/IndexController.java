package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.form.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/index")
    public ModelAndView index(@ModelAttribute("searchForm") final SearchForm form) {

        return new ModelAndView("index").addObject("loggedIn", isLogged());
    }

    @RequestMapping(value = "/index", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("searchForm") final SearchForm form, final BindingResult errors) {
        if (errors.hasErrors()) {
            return index(form);
        }

        return new ModelAndView("redirect:/products?filter=" + form.getSearch());
    }

    private boolean isLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !authentication.getPrincipal().equals("anonymousUser");
    }
}
