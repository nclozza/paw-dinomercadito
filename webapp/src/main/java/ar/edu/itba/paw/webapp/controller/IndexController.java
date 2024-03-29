package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.ForgotPasswordService;
import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.interfaces.Services.ViewService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.View;
import ar.edu.itba.paw.webapp.form.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    PostService postService;

    @RequestMapping("/")
    public ModelAndView indexSlash(@ModelAttribute("searchForm") final SearchForm form) {
        return index(form, false);
    }

    @RequestMapping("/index")
    public ModelAndView index(@ModelAttribute("searchForm") final SearchForm form, boolean error) {

        Post firstPost = null;
        List<Post> mostVisitedPosts = postService.findMostVisitedPosts();
        if(!mostVisitedPosts.isEmpty())
            firstPost = mostVisitedPosts.remove(0);

        return new ModelAndView("index").addObject("error", error)
                .addObject("posts", mostVisitedPosts)
                .addObject("firstPost", firstPost);
    }

    @RequestMapping(value = "/index", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("searchForm") final SearchForm form, final BindingResult errors) {
        if (errors.hasErrors()) {
            return index(form, true);
        }

        return new ModelAndView("redirect:/products?filter=" + form.getSearch());
    }
}
