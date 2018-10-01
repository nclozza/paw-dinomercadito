package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.*;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.Transaction;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.form.EditPostForm;
import ar.edu.itba.paw.webapp.form.PostForm;
import ar.edu.itba.paw.webapp.form.TransactionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    PostService postService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private EmailService emailService;

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

        User user = getLoggedUser();
        Integer userId = user.getUserId();
        final Post post = postService.createPost(form.getProductId(), Double.valueOf(form.getPrice()), userId,
                form.getDescription(), form.getProductQuantity());

        // TODO change the redirect
        return new ModelAndView("redirect:/posts?productId=" + post.getProductId());
    }

    @RequestMapping(value = "/post", method = {RequestMethod.GET})
    public ModelAndView post(@RequestParam(value = "postId") final Integer postId,
                             @ModelAttribute("transactionForm") final TransactionForm form) {
        ModelAndView mav = new ModelAndView("post");
        Post post = postService.findPostByPostId(postId);
        User user = userService.findUserByUserId(post.getUserId());
        Product product = productService.findProductByProductId(post.getProductId());

        mav.addObject("post", post);
        mav.addObject("user", user);
        mav.addObject("product", product);

        mav.addObject("found_error", false);

        return mav;
    }

    private User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByUsername(authentication.getName());
    }

    @RequestMapping(value = "/post", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("transactionForm") final TransactionForm form,
                               final BindingResult errors) {

        if (errors.hasErrors() || form.getProductQuantity() <= 0) {
            return post(form.getPostId(), form);
        }

        User user = getLoggedUser();

        Integer status = transactionService.makeTransaction(user.getUserId(), form.getPostId(), form.getProductQuantity());

        if (status.equals(Transaction.INCOMPLETE)) {
            return new ModelAndView("redirect:/500");

        } else if (status.equals(Transaction.OUT_OF_STOCK_FAIL)) {
            errors.addError(new FieldError("transactionForm", "productQuantity", ""));
            return post(form.getPostId(), form);

        } else if (status.equals(Transaction.INSUFFICIENT_FUNDS_FAIL)) {
            return post(form.getPostId(), form).addObject("found_error", true);
        }

        Optional<Transaction> transaction = transactionService.findTransactionByTransactionId(status);
        Post post = postService.findPostByPostId(transaction.get().getPostId());
        User seller = userService.findUserByUserId(post.getUserId());

        emailService.sendSuccessfulPurchaseEmail(user.getEmail(), transaction.get().getProductName(), form.getPostId());
        emailService.sendSuccesfulSaleEmail(seller.getEmail(), transaction.get().getProductName(), form.getPostId());

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/editPost", method = {RequestMethod.GET})
    public ModelAndView edit(@RequestParam(value = "postId") final Integer postId,
                             @ModelAttribute("editPost") final EditPostForm form) {

        Post post = postService.findPostByPostId(postId);
        User user = getLoggedUser();

        if (!user.getUserId().equals(post.getUserId())) {
            return new ModelAndView("redirect:/403");
        }

        ModelAndView mav = new ModelAndView("editPost");
        List<Product> productList = productService.findAllProducts();

        mav.addObject("productList", productList);
        mav.addObject("post", post);

        return mav;
    }

    @RequestMapping(value = "/editPost", method = {RequestMethod.POST})
    public ModelAndView editPost(@Valid @ModelAttribute("editPost") final EditPostForm form,
                               final BindingResult errors) {

        if (errors.hasErrors()) {
            return edit(form.getPostId(), form);
        }

        postService.updatePost(form.getPostId(), form.getProductId(), Double.valueOf(form.getPrice()),
                form.getDescription(), form.getProductQuantity());

        // TODO change the redirect
        return new ModelAndView("redirect:/post?postId=" + form.getPostId());
    }
}
