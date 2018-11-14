package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.*;
import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.webapp.form.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserReviewService userReviewService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    @Qualifier("userNotAuthenticatedServiceImpl")
    private UserNotAuthenticatedService usn;

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/signUp", method = {RequestMethod.GET})
    public ModelAndView signUp(@ModelAttribute("registerForm") final UserForm form) {
        return new ModelAndView("signUp")
                .addObject("username_repeated", false)
                .addObject("repeat_password", false);
    }

    @RequestMapping(value = "/signUp", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("registerForm") final UserForm form, final BindingResult errors) {
        if (errors.hasErrors()) {
            return signUp(form);
        }

        if (!form.checkPassword()) {
            errors.addError(new FieldError("registerForm", "repeatPassword", ""));
            return signUp(form);
        }

        if (!userService.checkUsername(form.getUsername()) || !usn.checkUsername(form.getUsername())) {
            return signUp(form).addObject("sameUsername_error", true);
        }

        String date = userService.getTodayDate();

        Integer code = usn.generateCode();

        final UserNotAuthenticated user = usn.createUser(form.getUsername(), form.getPassword(), form.getEmail(),
                form.getPhone(), form.getBirthdate(), date, code);

        emailService.sendCodeEmail(user.getEmail(), code);

        return new ModelAndView("redirect:/authentication");
    }

    @ModelAttribute("userId")
    public Integer loggedUser(final HttpSession session) {
        return (Integer) session.getAttribute("userid");
    }

    private User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findUserByUsername(authentication.getName());

        return user.orElse(null);
    }

    @RequestMapping(value = "/profile", method = {RequestMethod.GET})
    public ModelAndView profile(@ModelAttribute("updateProfileForm") final UpdateProfileForm form) {
        ModelAndView mav = new ModelAndView("profile");

        User user = getLoggedUser();
        Integer userId = user.getUserId();
        List<Transaction> buyListPending = transactionService.findBuysByUserIdAndStatus(userId, Transaction.PENDING);
        List<Transaction> buyListConfirmed = transactionService.findBuysByUserIdAndStatus(userId, Transaction.CONFIRMED);
        List<Transaction> sellListPending = transactionService.findSellsByUserIdAndStatus(userId, Transaction.PENDING);
        List<Transaction> sellListConfirmed = transactionService.findSellsByUserIdAndStatus(userId, Transaction.CONFIRMED);
        List<Post> postList = postService.findPostsByUserId(userId);
        postList.sort(Comparator.comparing(Post::getVisits).reversed());
        List<Question> pendigQuestionList = questionService.findPendingQuestionsByUserId(userId);
        List<Question> myQuestionList = questionService.findQuestionsByUserWhoAskId(userId);

        mav.addObject("formError", false);
        mav.addObject("repeat_password", false);
        mav.addObject("password_error", false);
        mav.addObject("invalid_transaction", false);
        mav.addObject("user", user);
        mav.addObject("pendingQuestions", pendigQuestionList);
        mav.addObject("myQuestions", myQuestionList);
        mav.addObject("pendingSells", sellListPending);
        mav.addObject("pendingBuys", buyListPending);
        mav.addObject("confirmedSells", sellListConfirmed);
        mav.addObject("confirmedBuys", buyListConfirmed);
        mav.addObject("posts", postList);

        return mav;
    }

    @RequestMapping(value = "/profile", method = {RequestMethod.POST})
    public ModelAndView updateUser(@Valid @ModelAttribute("updateProfileForm") final UpdateProfileForm form,
                                   final BindingResult errors) {

        if (errors.hasErrors()) {
            return profile(form).addObject("form_error", true);
        }

        User user = getLoggedUser();

        if (form.getPassword().equals("")) {
            if (!form.checkPassword()) {
                return profile(form).addObject("repeat_password", true);
            }

            userService.updateUserWithoutPasswordEncoder(user.getUserId(), user.getPassword(), form.getEmail(),
                    form.getPhone(), form.getBirthdate());

        } else if (form.getPassword().length() < 6 || form.getPassword().length() > 32) {
            return profile(form).addObject("password_error", true);

        } else if (!form.checkPassword()) {
            return profile(form).addObject("repeat_password", true);

        } else {
            userService.updateUser(user.getUserId(), form.getPassword(), form.getEmail(), form.getPhone(),
                    form.getBirthdate());
        }

        return new ModelAndView("redirect:/profile");
    }

    @RequestMapping(value = "/confirmTransaction", method = {RequestMethod.POST})
    public ModelAndView confirmTransaction(@Valid @ModelAttribute("updateProfileForm") final UpdateProfileForm form,
                                           final BindingResult errors) {

        Optional<Transaction> transaction = transactionService.findTransactionByTransactionId(form.getTransactionId());

        if (!transactionService.isValidTransaction(transaction.get())) {
            return profile(form).addObject("invalid_transaction", true);
        }

        transactionService.confirmTransaction(transaction.get());

        emailService.sendSuccessfulPurchaseEmail(transaction.get().getBuyerUser().getEmail(),
                transaction.get().getProductName(), transaction.get().getPostBuyed().getPostId(),
                transaction.get().getPostBuyed().getUserSeller().getUserId());

        return new ModelAndView("redirect:/profile");
    }

    @RequestMapping(value = "/declineTransaction", method = {RequestMethod.POST})
    public ModelAndView declineTransaction(@Valid @ModelAttribute("updateProfileForm") final UpdateProfileForm form,
                                           final BindingResult errors) {

        User user = getLoggedUser();
        Optional<Transaction> transaction = transactionService.findTransactionByTransactionId(form.getTransactionId());

        if (!transaction.isPresent() || user == null
                || !user.getUserId().equals(transaction.get().getBuyerUser().getUserId())) {
            return new ModelAndView("redirect:/400");
        }

        transactionService.changeTransactionStatus(form.getTransactionId(), Transaction.DECLINED);

        String sellerUserEmail = transaction.get().getPostBuyed().getUserSeller().getEmail();

        emailService.sendDeclinedTransaction(sellerUserEmail, user.getUsername(), transaction.get().getProductName());

        return new ModelAndView("redirect:/profile");
    }


    @RequestMapping(value = "/authentication", method = {RequestMethod.GET})
    public ModelAndView authentication(@ModelAttribute("authenticationForm") final AuthenticationForm form) {
        return new ModelAndView("authentication");
    }

    @RequestMapping(value = "/authentication", method = {RequestMethod.POST})
    public ModelAndView authenticate(@Valid @ModelAttribute("authenticationForm") final AuthenticationForm form,
                                     final BindingResult errors) {
        if (errors.hasErrors()) {
            return authentication(form);
        }

        Optional<UserNotAuthenticated> user = usn.findUserByCode(form.getCode());
        String date = userService.getTodayDate();

        if (!user.isPresent()) {
            errors.addError(new FieldError("authenticationForm", "code", ""));
            return authentication(form);

        } else if (!user.get().getSignUpDate().equals(date)) {
            return authentication(form).addObject("code_expired", true);

        } else {
            User userAuthenticated = userService.createUser(user.get().getUsername(), user.get().getPassword(),
                    user.get().getEmail(), user.get().getPhone(), user.get().getBirthdate());

            usn.deleteUser(user.get().getUserId());
            emailService.sendSuccessfulRegistrationEmail(userAuthenticated.getEmail(), userAuthenticated.getUsername());

            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping("/sellerInformation")
    public ModelAndView sellerInformation(@RequestParam(value = "transactionId") final Integer transactionId) {
        User user = getLoggedUser();
        Optional<Transaction> transaction = transactionService.findTransactionByTransactionId(transactionId);

        if (!transaction.isPresent()) {
            LOGGER.error("TransactionId does not exist");
            return new ModelAndView("redirect:/400");
        }

        Optional<Post> post = postService.findPostByPostId(transaction.get().getPostBuyed().getPostId());

        if (!post.isPresent()) {
            LOGGER.error("PostId does not exist");
            return new ModelAndView("redirect:/500");
        }

        Optional<User> sellerUser = userService.findUserByUserId(post.get().getUserSeller().getUserId());

        if (!sellerUser.isPresent()) {
            LOGGER.error("Seller id does not exits");
            return new ModelAndView("redirect:/400");
        }

        if (!transaction.get().getBuyerUser().getUserId().equals(user.getUserId())) {
            LOGGER.error("BuyerId does not match the transaction buyerId");
            return new ModelAndView("redirect:/400");
        }

        ModelAndView mav = new ModelAndView("sellerInformation");
        mav.addObject("sellerUser", sellerUser.get());
        return mav;
    }


    @RequestMapping(value = "/userReview", method = {RequestMethod.GET})
    public ModelAndView userReview(@ModelAttribute("userReview") final UserReviewForm form,
                                   @RequestParam(value = "filter", required = false) final String filter,
                                   @RequestParam(value = "postId") final Integer postId,
                                   @RequestParam(value = "profile") final Boolean profile,
                                   @RequestParam(value = "userId") final Integer userId) {
        Optional<Post> post = postService.findPostByPostId(postId);

        if (!post.isPresent()) {
            LOGGER.error("PostId does not exits");
            return new ModelAndView("redirect:/400");
        }
        Optional<User> user = userService.findUserByUserId(userId);

        if (!user.isPresent()) {
            LOGGER.error("UserId does not exits");
            return new ModelAndView("redirect:/400");
        }

        return new ModelAndView("userReview").addObject("userId", userId)
                .addObject("filter", filter)
                .addObject("postId", postId)
                .addObject("profile", profile);
    }

    @RequestMapping(value = "/userReview", method = {RequestMethod.POST})
    public ModelAndView createUserReview(@Valid @ModelAttribute("userReview") final UserReviewForm form,
                                         final BindingResult errors) {
        if (errors.hasErrors())
            return userReview(form, form.getFilter(), form.getPostId(), form.getProfile(), form.getUserId());

        User userLogged = getLoggedUser();

        if (userLogged.getUserId().equals(form.getUserId()))
            return userReview(form, form.getFilter(), form.getPostId(), form.getProfile(),
                    form.getUserId()).addObject("same_user_error", true);

        if (!userReviewService.checkUserWhoReview(userLogged.getUserId(), form.getUserId()))
            return userReview(form, form.getFilter(), form.getPostId(), form.getProfile(),
                    form.getUserId()).addObject("check_user_error", true);

        if (!transactionService.findTransactionsByUserIdAndPostId(userLogged.getUserId(), form.getPostId()))
            return userReview(form, form.getFilter(), form.getPostId(), form.getProfile(),
                    form.getUserId()).addObject("already_buyer_error", true);

        userService.addRating(form.getUserId(), form.getRating());
        userReviewService.createUserReview(form.getUserId(), userLogged.getUserId(), form.getRating(), form.getDescription());

        Optional<Post> post = postService.findPostByPostId(form.getPostId());

        if (!post.isPresent()) {
            return new ModelAndView("redirect:/400");
        }

        emailService.sendReviewEmail(post.get().getUserSeller().getEmail(), userLogged.getUsername(),
                form.getDescription(), form.getRating());

        return userReviews(form.getFilter(), form.getPostId(), form.getProfile(), form.getUserId(), form);
    }

    @RequestMapping(value = "/userReviews", method = {RequestMethod.GET})
    public ModelAndView userReviews(@RequestParam(value = "filter", required = false) final String filter,
                                    @RequestParam(value = "postId") final Integer postId,
                                    @RequestParam(value = "profile", required = false) final Boolean profile,
                                    @RequestParam(value = "userId") final Integer userId,
                                    @ModelAttribute("userReview") final UserReviewForm form) {


        Optional<User> user = userService.findUserByUserId(userId);

        if (!user.isPresent()) {
            LOGGER.error("UserId does not exits");
            return new ModelAndView("redirect:/400");
        }

        Optional<Post> post = postService.findPostByPostId(postId);

        if (!post.isPresent()) {
            LOGGER.error("PostId does not exits");
            return new ModelAndView("redirect:/400");
        }

        List<UserReview> userReviewList = userReviewService.findReviewsByUserReviewedId(userId);

        ModelAndView mav = new ModelAndView("userReviews").addObject("userId", userId)
                .addObject("filter", filter)
                .addObject("postId", postId)
                .addObject("profile", profile)
                .addObject("userReviews", userReviewList)
                .addObject("alreadyBuy", transactionService.findTransactionsByUserIdAndPostId(getLoggedUser().getUserId(), postId));

        return mav;
    }

    @RequestMapping(value = "/answer", method = {RequestMethod.GET})
    public ModelAndView answer(@ModelAttribute("answer") final AnswerForm form,
                               @RequestParam(value = "questionId") final Integer questionId) {

        Optional<Question> question = questionService.findQuestionsByQuestionId(questionId);

        if (!question.isPresent()) {
            LOGGER.error("QuestionId does not exits");
            return new ModelAndView("redirect:/400");
        }

        return new ModelAndView("answer").addObject("question", question.get());
    }

    @RequestMapping(value = "/answer", method = {RequestMethod.POST})
    public ModelAndView answerQuestion(@Valid @ModelAttribute("answer") final AnswerForm form,
                                       final BindingResult errors) {
        if (errors.hasErrors())
            return answer(form, form.getQuestionId());

        Optional<Question> question = questionService.findQuestionsByQuestionId(form.getQuestionId());
        User userLogged = getLoggedUser();

        if (!question.isPresent() || !userLogged.getUserId().equals(question.get().getPostAsked().getUserSeller().getUserId())) {
            return answer(form, form.getQuestionId()).addObject("answer_error", true);
        }

        questionService.addAnswer(form.getQuestionId(), form.getAnswer());

        emailService.sendAnswerEmail(question.get().getUserWhoAsk().getEmail(),
                question.get().getPostAsked().getProductPosted().getProductName(), form.getAnswer());

        return new ModelAndView("redirect:/profile");
    }

    @RequestMapping(value = "/forgotPassword", method = {RequestMethod.GET})
    public ModelAndView forgotPassword(@ModelAttribute("forgotPassword") final ForgotPasswordForm form) {

        return new ModelAndView("forgotPassword");
    }

    @RequestMapping(value = "/forgotPassword", method = {RequestMethod.POST})
    public ModelAndView forgotPasswordRequest(@Valid @ModelAttribute("forgotPassword") final ForgotPasswordForm form,
                                              final BindingResult errors) {

        if (errors.hasErrors())
            return forgotPassword(form);

        Optional<User> user = userService.findUserByUsername(form.getUsername());

        if (!user.isPresent())
            return forgotPassword(form).addObject("wrong_user", true);

        if (!user.get().getEmail().equals(form.getEmail()))
            return forgotPassword(form).addObject("wrong_email", true);

        forgotPasswordService.createNewRequest(user.get(), userService.getTodayDate());

        return new ModelAndView("checkEmail");
    }


    @RequestMapping(value = "/changePassword", method = {RequestMethod.GET})
    public ModelAndView changePassword(@ModelAttribute("changePassword") final ChangePasswordForm form,
                                       @RequestParam(value = "code") final String code) {

        return new ModelAndView("changePassword").addObject("code", code);
    }

    @RequestMapping(value = "/changePassword", method = {RequestMethod.POST})
    public ModelAndView changePasswordRequest(@Valid @ModelAttribute("changePassword") final ChangePasswordForm form,
                                              final BindingResult errors) {
        if (errors.hasErrors())
            return changePassword(form, form.getCode());

        if (!form.checkPassword())
            return changePassword(form, form.getCode()).addObject("different_password", true);

        Integer code = form.getCode().hashCode();
        Optional<ForgotPassword> forgotPassword = forgotPasswordService.findRequestByCode(code.toString());
        String date = userService.getTodayDate();

        if (!forgotPassword.isPresent() || !date.equals(forgotPassword.get().getRequestDate()))
            return changePassword(form, form.getCode()).addObject("invalid_code", true);

        User user = forgotPassword.get().getUserForgot();
        userService.updateUser(user.getUserId(), form.getPassword(), user.getEmail(), user.getPhone(), user.getBirthdate());
        forgotPasswordService.deleteRequestById(forgotPassword.get().getForgotPasswordId());

        return new ModelAndView("redirect:/login");
    }

}