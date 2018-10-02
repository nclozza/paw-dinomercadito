package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.UserDAO;
import ar.edu.itba.paw.interfaces.Services.AddressService;
import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.Address;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public User createUserWithAddress(final String username, final String password, final String email,
                                      final String phone, final String birthdate, final String street,
                                      final Integer number, final String city, final String province,
                                      final String zipCode, final String country) {

        User user = userDAO.createUser(username, password, email, phone, birthdate, 0.0);

        addressService.createAddress(user.getUserId(), street, number, city, province, zipCode, country);

        return user;
    }

    @Override
    public User createUser(final String username, final String password, final String email, final String phone,
                           final String birthdate) {

        return userDAO.createUser(username, password, email, phone, birthdate, 0.0);
    }

    @Override
    public User createUser(final String username, final String password, final String email, final String phone,
                           final String birthdate, final Double funds) {


        return userDAO.createUser(username, password, email, phone, birthdate, funds);
    }

    @Override
    public User findUserByUsername(final String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public User updateUserWithoutPasswordEncoder(final Integer userId, final String password, final String email,
                                                 final String phone, final String birthdate, final Double funds) {

        return userDAO.updateUser(userId, password, email, phone, birthdate, funds);
    }

    @Override
    public User findUserByUserId(final Integer userId) {
        return userDAO.findUserByUserId(userId);
    }

    // TODO See what to do with this method's return value
    @Override
    public boolean deleteUser(final Integer userId) {
        boolean deletionSucceeded = true;

        List<Post> postsList = postService.findPostByUserId(userId);

        if (postsList != null && !postsList.isEmpty()) {
            for (Post post : postsList) {
                postService.deletePost(post.getPostId());
            }
        } else {
            return !deletionSucceeded;
        }

        List<Address> addressesList = addressService.findAddressByUserId(userId);

        if (addressesList != null && !addressesList.isEmpty()) {
            for (Address address : addressesList) {
                addressService.deleteAddress(address.getAddressId());
            }
        } else {
            return !deletionSucceeded;
        }

        if (userDAO.deleteUser(userId) == deletionSucceeded)
            return deletionSucceeded;
        else
            return !deletionSucceeded;
    }

    @Override
    public User updateUser(final Integer userId, final String password, final String email, final String phone,
                           final String birthdate, final Double funds) {
        return userDAO.updateUser(userId, passwordEncoder.encode(password), email, phone, birthdate, funds);
    }

    @Override
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

    @Override
    public boolean checkUsername(final String username) {
        return userDAO.checkUsername(username);
    }

    @Override
    public boolean addFundsToUserId(final Double funds, final Integer userId) {
        return userDAO.addFundsToUserId(funds, userId);
    }
}
