package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;

import java.time.LocalDate;

public interface UserDAO {

    /**
     * Create a new user.
     *
     * @param username The user's username.
     * @return The created user.
     */
    public User createUser(final String username, final String password, final String email, final String phone,
                           final String address, final LocalDate birthdate);
    public User findUserById(final Integer userId);
    public boolean deleteUser(final Integer userId);
    public boolean updateUser(final String username, final String password, final String email, final String phone, final String address, final LocalDate birthdate);
    public boolean buyProduct(final Integer buyerId, final Integer sellerId, final Integer postId);
    public Post postProduct(final Product product, final Double price, final Integer userId, final String description);
}
