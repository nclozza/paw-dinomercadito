package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.form.PostForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class PostsController {

    @Autowired
    PostService postService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @RequestMapping("/posts")
    public ModelAndView index(@RequestParam(value = "productId") final Integer productId) {
        ModelAndView mav = new ModelAndView("posts");
        List<Post> postList = postService.findPostsByProductId(productId);

        mav.addObject("posts", postList);

        return mav;
    }

    @RequestMapping(value = "/newPost", method = {RequestMethod.GET})
    public ModelAndView newPost(@ModelAttribute("registerForm") final PostForm form) {
        ModelAndView mav = new ModelAndView("newPost");
        List<Product> productList = productService.findAllProducts();

        mav.addObject("productList", productList);

        return mav;
    }

    @RequestMapping(value = "/newPost", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("registerForm") final PostForm form, final BindingResult errors) {
        if (errors.hasErrors()) {
            return newPost(form);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(authentication.getName());
        Integer userId = user.getUserId();
        final Post post = postService.createPost(form.getProductId(), Double.valueOf(form.getPrice()), userId,
                form.getDescription(), form.getProductQuantity());

        // TODO change the redirect
        return new ModelAndView("redirect:/posts?productId=" + post.getProductId());
    }

    @RequestMapping("/post")
    public ModelAndView post(@RequestParam(value = "postId") final Integer postId) {
        ModelAndView mav = new ModelAndView("post");
        Post post = postService.findPostByPostId(postId);
        User user = userService.findUserByUserId(post.getUserId());
        Product product = productService.findProductByProductId(post.getProductId());

        mav.addObject("post", post);
        mav.addObject("user", user);
        mav.addObject("product", product);

        return mav;
    }
}
