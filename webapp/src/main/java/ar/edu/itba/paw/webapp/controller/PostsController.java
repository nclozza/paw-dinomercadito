package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.*;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.Question;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.form.EditPostForm;
import ar.edu.itba.paw.webapp.form.PostForm;
import ar.edu.itba.paw.webapp.form.QuestionForm;
import ar.edu.itba.paw.webapp.form.TransactionForm;
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
import java.util.Comparator;
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
    QuestionService questionService;

    @RequestMapping("/posts")
    public ModelAndView index(@RequestParam(value = "filter", required = false) final String filter, @RequestParam(value = "productId") final Integer productId) {
        ModelAndView mav = new ModelAndView("posts");
        Optional<Product> product = productService.findProductByProductId(productId);

        if (!product.isPresent()) {
            return new ModelAndView("redirect:/400");
        }

        List<Post> postList = postService.findPostsByProductId(productId);

        postList = postService.filterByAvailablePosts(postList);

        postList.sort(Comparator.comparing(Post::getVisits).reversed());

        mav.addObject("posts", postList);
        mav.addObject("product", product.get());
        mav.addObject("filter", filter);

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

        Optional<User> user = getLoggedUser();
        Integer userId = user.get().getUserId();
        final Post post = postService.createPost(form.getProductId(), Double.valueOf(form.getPrice()), userId,
                form.getDescription(), form.getProductQuantity(), 0);

        return new ModelAndView("redirect:/post?postId=" + post.getPostId());
    }

    @RequestMapping(value = "/post", method = {RequestMethod.GET})
    public ModelAndView post(@RequestParam(value = "filter", required = false) final String filter,
                             @RequestParam(value = "postId") final Integer postId,
                             @RequestParam(value = "profile", required = false) final Boolean profile,
                             @ModelAttribute("transactionForm") final TransactionForm form) {
        ModelAndView mav = new ModelAndView("post");
        Optional<Post> post = postService.findPostByPostId(postId);

        if (!post.isPresent()) {
            return new ModelAndView("redirect:/404");
        }

        Optional<User> user = userService.findUserByUserId(post.get().getUserId());
        Optional<Product> product = productService.findProductByProductId(post.get().getProductId());

        if (!user.isPresent() || !product.isPresent()) {
            return new ModelAndView("redirect:/404");
        }

        Optional<Post> postModified = postService.addVisit(post.get().getPostId(), user.get().getUserId(), getLoggedUser().get().getUserId());

        if (postModified != null)
            post = postModified;

        mav.addObject("post", post.get());
        mav.addObject("user", user.get());
        mav.addObject("product", product.get());
        mav.addObject("filter", filter);
        if(profile != null)
            mav.addObject("profile", profile);
        else
            mav.addObject("profile", false);

        return mav;
    }

    private Optional<User> getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findUserByUsername(authentication.getName());

        return authentication.isAuthenticated() ? user : null;
    }

//    @RequestMapping(value = "/post", method = {RequestMethod.POST})
//    public ModelAndView buy(@Valid @ModelAttribute("transactionForm") final TransactionForm form,
//                               final BindingResult errors) {
//
//        if (errors.hasErrors()) {
//            return post(form.getPostId(), form);
//        }
//
//        User user = getLoggedUser();
//
//
//        Integer status = transactionService.makeTransaction(user.getUserId(), form.getPostId(), form.getProductQuantity());
//
//        if (status.equals(Transaction.SAME_USER)){
//            errors.addError(new FieldError("transactionForm", "postId", ""));
//            return post(form.getPostId(), form);
//        }
//        else if (status.equals(Transaction.INCOMPLETE)) {
//            return new ModelAndView("redirect:/500");
//
//        } else if (status.equals(Transaction.WRONG_PARAMETERS)) {
//            return new ModelAndView("redirect:/400");
//
//        } else if (status.equals(Transaction.OUT_OF_STOCK_FAIL)) {
//            errors.addError(new FieldError("transactionForm", "productQuantity", ""));
//            return post(form.getPostId(), form);
//
//        } else if (status.equals(Transaction.INSUFFICIENT_FUNDS_FAIL)) {
//            return post(form.getPostId(), form).addObject("funds_error", true);
//        }
//
//        Optional<Transaction> transaction = transactionService.findTransactionByTransactionId(status);
//
//        if (!transaction.isPresent()) {
//            return new ModelAndView("redirect:/500");
//        }
//
//        Optional<Post> post = postService.findPostByPostId(transaction.get().getPostId());
//
//        if (!post.isPresent()) {
//            return new ModelAndView("redirect:/400");
//        }
//
//        Optional<User> seller = userService.findUserByUserId(post.get().getUserId());
//
//        if (!seller.isPresent()) {
//            return new ModelAndView("redirect:/404");
//        }
//
//        emailService.sendSuccessfulPurchaseEmail(user.getEmail(), transaction.get().getProductName(), form.getPostId());
//        emailService.sendSuccessfulSaleEmail(seller.get().getEmail(), transaction.get().getProductName(), form.getPostId());
//
//        return new ModelAndView("redirect:/sellerInformation?transactionId=" + transaction.get().getTransactionId());
//    }

    @RequestMapping(value = "/editPost", method = {RequestMethod.GET})
    public ModelAndView edit(@RequestParam(value = "postId") final Integer postId,
                             @ModelAttribute("editPost") final EditPostForm form) {

        Optional<Post> post = postService.findPostByPostId(postId);

        if (!post.isPresent()) {
            return new ModelAndView("redirect:/400");
        }

        Optional<User> user = getLoggedUser();

        if (!user.get().getUserId().equals(post.get().getUserId())) {
            return new ModelAndView("redirect:/400");
        }

        ModelAndView mav = new ModelAndView("editPost");
        List<Product> productList = productService.findAllProducts();

        mav.addObject("productList", productList);
        mav.addObject("post", post.get());

        return mav;
    }

    @RequestMapping(value = "/editPost", method = {RequestMethod.POST})
    public ModelAndView editPost(@Valid @ModelAttribute("editPost") final EditPostForm form,
                                 final BindingResult errors) {

        if (errors.hasErrors()) {
            return edit(form.getPostId(), form);
        }

        Optional<Post> post = postService.findPostByPostId(form.getPostId());

        postService.updatePost(form.getPostId(), form.getProductId(), Double.valueOf(form.getPrice()),
                form.getDescription(), form.getProductQuantity(), post.get().getVisits());

        return new ModelAndView("redirect:/post?postId=" + form.getPostId() + "&&profile=true");
    }

    @RequestMapping(value = "/question", method = {RequestMethod.GET})
    public ModelAndView ask(@Valid @ModelAttribute("question") final QuestionForm form,
                            @RequestParam(value = "postId") final Integer postId,
                            @RequestParam(value = "filter", required = false) final String filter,
                            @RequestParam(value = "profile", required = false) final Boolean profile) {

        Optional<Post> post = postService.findPostByPostId(postId);

        return new ModelAndView("question").addObject("post", post.get())
                .addObject("filter", filter)
                .addObject("profile", profile);
    }

    @RequestMapping(value = "/question", method = {RequestMethod.POST})
    public ModelAndView createQuestion(@Valid @ModelAttribute("question") final QuestionForm form,
                                       final BindingResult errors) {
        if(errors.hasErrors())
            return ask(form, form.getPostId(), form.getFilter(), form.getProfile());

        Optional<Post> post = postService.findPostByPostId(form.getPostId());
        Optional<User> userLogged = getLoggedUser();

        if(userLogged.get().getUserId() == post.get().getUserId())
            return ask(form, form.getPostId(), form.getFilter(), form.getProfile()).addObject("same_user_question", true);

        Question question = questionService.createQuestion(post.get().getPostId(), userLogged.get().getUserId(), form.getQuestion());

        return questions(form, form.getFilter(), form.getProfile(), form.getPostId());
    }

    @RequestMapping(value = "/questions", method = {RequestMethod.GET})
    public ModelAndView questions(@Valid @ModelAttribute("questions") final QuestionForm form,
                                  @RequestParam(value = "filter", required = false) final String filter,
                                  @RequestParam(value = "profile", required = false) final Boolean profile,
                                  @RequestParam(value = "postId") final Integer postId){

        List<Question> questionList = questionService.findQuestionsByPostId(postId);

        ModelAndView mav = new ModelAndView("questions")
                .addObject("filter", filter )
                .addObject("profile", profile)
                .addObject("postId", postId)
                .addObject("questions", questionList);

        if(questionList.isEmpty())
            mav.addObject("empty_questions", true);

        return mav;
    }
}
