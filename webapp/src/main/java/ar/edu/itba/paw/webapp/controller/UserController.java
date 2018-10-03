package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.*;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Transaction;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.UserNotAuthenticated;
import ar.edu.itba.paw.webapp.form.AddFundsForm;
import ar.edu.itba.paw.webapp.form.AuthenticationForm;
import ar.edu.itba.paw.webapp.form.UpdateUserForm;
import ar.edu.itba.paw.webapp.form.UserForm;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            return signUp(form).addObject("repeat_password", true);
        }

        if (!userService.checkUsername(form.getUsername()) || !usn.checkUsername(form.getUsername())) {
            return signUp(form).addObject("username_repeated", true);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

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
    public ModelAndView profile(@ModelAttribute("updateUserForm") final UpdateUserForm form) {
        ModelAndView mav = new ModelAndView("profile");

        User user = getLoggedUser();
        Integer userId = user.getUserId();
        List<Transaction> transactionList = transactionService.findTransactionsByBuyerUserId(userId);
        List<Post> postList = postService.findPostsByUserId(userId);

        mav.addObject("formError", false);
        mav.addObject("repeat_password", false);
        mav.addObject("password_error", false);
        mav.addObject("user", user);

        mav.addObject("transactions", transactionList);
        mav.addObject("posts", postList);

        return mav;
    }

    @RequestMapping(value = "/profile", method = {RequestMethod.POST})
    public ModelAndView updateUser(@Valid @ModelAttribute("updateUserForm") final UpdateUserForm form,
                                   final BindingResult errors) {

        if (errors.hasErrors()) {
            return profile(form).addObject("form_error", true);
        }

        if (form.getPassword().equals("")) {
            if (!form.checkPassword()) {
                return profile(form).addObject("repeat_password", true);
            }

            User user = getLoggedUser();

            userService.updateUserWithoutPasswordEncoder(user.getUserId(), user.getPassword(), form.getEmail(),
                    form.getPhone(), form.getBirthdate(), user.getFunds());

        } else if (form.getPassword().length() < 6 || form.getPassword().length() > 32) {
            return profile(form).addObject("password_error", true);

        } else if (!form.checkPassword()) {
            return profile(form).addObject("repeat_password", true);

        } else {

            User user = getLoggedUser();

            userService.updateUser(user.getUserId(), form.getPassword(), form.getEmail(), form.getPhone(),
                    form.getBirthdate(), user.getFunds());
        }

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

        if (!user.isPresent()) {
            errors.addError(new FieldError("authenticationForm", "code", ""));
            return authentication(form);

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

        Optional<Post> post = postService.findPostByPostId(transaction.get().getPostId());

        if (!post.isPresent()) {
            LOGGER.error("PostId does not exist");
            return new ModelAndView("redirect:/500");
        }

        Optional<User> sellerUser = userService.findUserByUserId(post.get().getUserId());

        if (!sellerUser.isPresent()) {
            LOGGER.error("Seller id does not exits");
            return new ModelAndView("redirect:/400");
        }

        if (!transaction.get().getBuyerUserId().equals(user.getUserId())) {
            LOGGER.error("BuyerId does not match the transaction buyerId");
            return new ModelAndView("redirect:/400");
        }

        ModelAndView mav = new ModelAndView("sellerInformation");
        mav.addObject("sellerUser", sellerUser.get());
        return mav;
    }

    @RequestMapping(value = "/profile/addFunds", method = {RequestMethod.GET})
    public ModelAndView addFunds(@ModelAttribute("addFundsForm") final AddFundsForm form) {
        return new ModelAndView("addFunds");
    }

    @RequestMapping(value = "/profile/addFunds", method = {RequestMethod.POST})
    public ModelAndView addFundsPost(@Valid @ModelAttribute("addFundsForm") final AddFundsForm form) {

        User user = getLoggedUser();

        if (!userService.addFundsToUserId(Double.valueOf(form.getFunds()), user.getUserId())) {
            return new ModelAndView("redirect:/500");
        }

        return new ModelAndView("redirect:/profile");
    }
}
