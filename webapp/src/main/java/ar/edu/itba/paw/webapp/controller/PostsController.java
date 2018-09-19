package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PostsController {

    @Autowired
    PostService postService;

    @RequestMapping("/posts")
    public ModelAndView index(@RequestParam(value = "productId", required = true) final Integer productId) {
        ModelAndView mav = new ModelAndView("posts");
        List<Post> postList = postService.findPostsByProductId(productId);
        mav.addObject("posts", postList);
        return mav;
    }
}
