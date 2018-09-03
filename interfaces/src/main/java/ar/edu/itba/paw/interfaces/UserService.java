package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;

import java.util.Date;

public interface UserService {

    /**
     * Create a new user.
     *
     * @param username The name of the user.
     * @return The created user.
     */
    public User createUser(final String username, final String password, final String email, final String phone,
                           final String address, final Date birthdate);
    public User getUser(final String username);
    public boolean deleteUser(final String username);
    public boolean updateUser(final String username, final String password, final String email, final String phone,
                              final String address, final Date birthdate);
    public boolean buy(final String buyerUsername, final String sellerUsername, final Integer postId);
    public Post postProduct(final Product product, final Double price, final String username, final String description);
}
