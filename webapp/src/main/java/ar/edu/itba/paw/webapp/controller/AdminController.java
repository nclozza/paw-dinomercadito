package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.webapp.form.DisablePostForm;
import ar.edu.itba.paw.webapp.form.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public ModelAndView index(@ModelAttribute("searchForm") final SearchForm form, boolean error) {

        return new ModelAndView("admin/index").addObject("error", error);
    }

    @RequestMapping(value = "/index", method = {RequestMethod.POST})
    public ModelAndView search(@Valid @ModelAttribute("searchForm") final SearchForm form, final BindingResult errors) {
        if (errors.hasErrors()) {
            return index(form, true);
        }

        return new ModelAndView("redirect:/admin/posts?filter="+ form.getSearch());
    }

    @RequestMapping(value = "/posts", method = {RequestMethod.GET})
    public ModelAndView posts(@ModelAttribute("disableForm") final DisablePostForm form,
                              @RequestParam(value = "filter", required = false) final String filter) {

        ModelAndView mav = new ModelAndView("admin/posts");

        List<Post> postList;

        if (filter == null || filter.isEmpty()) {
            postList = postService.findAllPosts();

        } else {
            postList = postService.findPostsByFilter(filter);
        }

        postList.sort(Comparator.comparing(Post::getDisable).reversed());

        mav.addObject("posts", postList);
        mav.addObject("filter", filter);

        return mav;
    }

    @RequestMapping(value = "/posts", method = {RequestMethod.POST})
    public ModelAndView postAction(@Valid @ModelAttribute("disablePost") final DisablePostForm form, final BindingResult errors) {

        postService.changePostStatus(form.getPostId());

        return new ModelAndView("redirect:/admin/posts?filter="+form.getFilter());
    }
}