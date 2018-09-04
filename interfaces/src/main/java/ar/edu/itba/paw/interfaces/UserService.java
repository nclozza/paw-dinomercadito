package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;

import java.time.LocalDate;

public interface UserService {
    User findUserByUsername(String username);

    /**
     * Create a new user.
     *
     * @param username The name of the user.
     * @return The created user.
     */
    User createUser(final String username, final String password, final String email, final String phone, final String address, final LocalDate birthdate);
    User getUser(final String username);
    boolean deleteUser(final String username);
    boolean updateUser(final String username, final String password, final String email, final String phone, final String address, final LocalDate birthdate);
    boolean buy(final String buyerUsername, final String sellerUsername, final Integer postId);
    Post postProduct(final Product product, final Double price, final String username, final String description);
}
