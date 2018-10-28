package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.webapp.form.SearchForm;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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

            List<Post> mostVisitedPosts = postService.findMostVisitedPosts();
            Post firstPost = mostVisitedPosts.remove(0);

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
