package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.UserDAO;
import ar.edu.itba.paw.interfaces.Services.*;
import ar.edu.itba.paw.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostService postService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TransactionService transactionService;

    public User createUserWithAddress(final String username, final String password, final String email,
                                      final String phone, final String birthdate, final String street,
                                      final Integer number, final String city, final String province,
                                      final String zipCode, final String country) {

        User user = userDAO.createUser(username, password, email, phone, birthdate, 0.0);

        addressService.createAddress(user.getUserId(), street, number, city, province, zipCode, country);

        return user;
    }

    public User createUser(final String username, final String password, final String email, final String phone,
                           final String birthdate) {

        return userDAO.createUser(username, password, email, phone, birthdate, 0.0);
    }

    public User createUser(final String username, final String password, final String email, final String phone,
                           final String birthdate, final Double funds) {


        return userDAO.createUser(username, password, email, phone, birthdate, funds);
    }

    @Transactional (readOnly = true)
    public Optional<User> findUserByUsername(final String username) {
        return userDAO.findUserByUsername(username);
    }

    @Transactional (readOnly = true)
    public Optional<User> findUserByUserId(final Integer userId) {
        return userDAO.findUserByUserId(userId);
    }

    @Override
    public Optional<User> updateUserWithoutPasswordEncoder(final Integer userId, final String password, final String email,
                                                 final String phone, final String birthdate, final Double funds) {
        return userDAO.updateUser(userId, password, email, phone, birthdate, funds);
    }

    public boolean deleteUser(final Integer userId) {
        boolean deletionSucceeded = true;

        List<Transaction> transactionsList = transactionService.findTransactionsByBuyerUserId(userId);

        if (!transactionsList.isEmpty()) {
            for (Transaction transaction : transactionsList)
                transactionService.deleteTransactionByTransactionId(transaction.getTransactionId());
        } else {
            return !deletionSucceeded;
        }

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

    public Optional<User> updateUser(final Integer userId, final String password, final String email, final String phone,
                                    final String birthdate, final Double funds) {
        return userDAO.updateUser(userId, passwordEncoder.encode(password), email, phone, birthdate, funds);
    }

    public boolean postProduct(final Integer productId, final Double price, final Integer userId,
                               final String description, final Integer productQuantity) {
        boolean postProductSucceeded = true;
        if (productId < 0 || price < 0.0 || userId < 0 || description == null)
            throw new IllegalArgumentException();

        if (postService.createPost(productId, price, userId, description, productQuantity) == null)
            return !postProductSucceeded;
        else
            return postProductSucceeded;
    }

    public boolean checkUsername(final String username) {
        return userDAO.checkUsername(username);
    }

    public boolean addFundsToUserId(final Double funds, final Integer userId) {
        return userDAO.addFundsToUserId(funds, userId);
    }
}
