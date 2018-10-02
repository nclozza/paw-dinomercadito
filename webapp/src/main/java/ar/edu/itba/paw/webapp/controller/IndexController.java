package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.webapp.form.SearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView index(@ModelAttribute("searchForm") final SearchForm form) {

        return new ModelAndView("index");
    }

    @RequestMapping(value = "/index", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("searchForm") final SearchForm form, final BindingResult errors) {
        if (errors.hasErrors()) {
            return index(form);
        }

        return new ModelAndView("redirect:/products?filter=" + form.getSearch());
    }
}
