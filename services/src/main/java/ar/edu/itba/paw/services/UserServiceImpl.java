package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.UserDAO;
import ar.edu.itba.paw.interfaces.Services.*;
import ar.edu.itba.paw.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.User;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostService postService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private TransactionService transactionService;

    @Autowired
    private UserReviewService userReviewService;

    @Override
    public User createUserWithAddress(final String username, final String password, final String email,
                                      final String phone, final String birthdate, final String street,
                                      final Integer number, final String city, final String province,
                                      final String zipCode, final String country) {

        User user = userDAO.createUser(username, password, email, phone, birthdate);

        addressService.createAddress(user.getUserId(), street, number, city, province, zipCode, country);

        return user;
    }

    @Override
    public User createUser(final String username, final String password, final String email, final String phone,
                           final String birthdate) {
        return userDAO.createUser(username, password, email, phone, birthdate);
    }

    @Transactional (readOnly = true)
    @Override
    public Optional<User> findUserByUsername(final String username) {
        return userDAO.findUserByUsername(username);
    }

    @Transactional (readOnly = true)
    @Override
    public Optional<User> findUserByUserId(final Integer userId) {
        return userDAO.findUserByUserId(userId);
    }

    @Override
    public Optional<User> updateUserWithoutPasswordEncoder(final Integer userId, final String password, final String email,
                                                 final String phone, final String birthdate) {
        return userDAO.updateUser(userId, password, email, phone, birthdate);
    }

    @Override
    public boolean deleteUser(final Integer userId) {
        boolean deletionSucceeded = true;

//        List<Transaction> transactionsList = transactionService.findTransactionsByBuyerUserId(userId);
//
//        if (!transactionsList.isEmpty()) {
//            for (Transaction transaction : transactionsList)
//                transactionService.deleteTransactionByTransactionId(transaction.getTransactionId());
//        } else {
//            return !deletionSucceeded;
//        }

        List<Post> postsList = postService.findPostsByUserId(userId);

        if (!postsList.isEmpty()) {
            for (Post post : postsList) {
                postService.deletePost(post.getPostId());
            }
        } else {
            return !deletionSucceeded;
        }

        List<Address> addressesList = addressService.findAddressByUserId(userId);

        if (!addressesList.isEmpty()) {
            for (Address address : addressesList) {
                addressService.deleteAddress(address.getAddressId());
            }
        } else {
            return !deletionSucceeded;
        }

        return userDAO.deleteUser(userId) == deletionSucceeded;
    }

    @Override
    public Optional<User> updateUser(final Integer userId, final String password, final String email, final String phone,
                                    final String birthdate) {
        return userDAO.updateUser(userId, passwordEncoder.encode(password), email, phone, birthdate);
    }

    @Override
    public boolean postProduct(final Integer productId, final Double price, final Integer userId,
                               final String description, final Integer productQuantity,final Integer visits) {
        boolean postProductSucceeded = true;
        if (productId < 0 || price < 0.0 || userId < 0 || description == null)
            throw new IllegalArgumentException();

        if (postService.createPost(productId, price, userId, description, productQuantity ,visits) == null)
            return !postProductSucceeded;
        else
            return postProductSucceeded;
    }

    @Override
    public boolean checkUsername(final String username) {
        return userDAO.checkUsername(username);
    }

//    public boolean addFundsToUserId(final Double funds, final Integer userId) {
//        return userDAO.addFundsToUserId(funds, userId);
//    }

    @Override
    public String getTodayDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    @Override
    public void addRating(Integer userId, Integer rating){
        Optional<User> user = userDAO.findUserByUserId(userId);
        List<UserReview> listUserReviewed = userReviewService.findReviewsByUserReviewedId(userId);
        Double newRating;

        if(user != null){
            Double userRating = user.get().getRating();
            if(userRating != null) {
                Integer cant = listUserReviewed.size();
                newRating = ((cant * userRating) + rating) / (cant + 1);
            } else {
                newRating = rating.doubleValue();
            }
            userDAO.addRating(user.get(), newRating);
            LOGGER.info("User rating = {} updated with userId = {}", rating, userId);
        } else {
            LOGGER.info("User not found with userId = {}", userId);
        }
    }
}
