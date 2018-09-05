package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;

import java.time.LocalDate;

public interface UserService {

    /**
     * Create a new user.
     *
     * @param username The name of the user.
     * @return The created user.
     */
    public User createUser(final String username, final String password, final String email, final String phone,
                           final Integer addressId, final LocalDate birthdate);
    public User findUserById(final Integer userId);
    public boolean deleteUser(final Integer userId);
    public boolean updateUserByFunds(final Integer userId, final Double funds);
    public boolean buyProduct(final Integer buyerId, final Integer sellerId, final Integer postId);
    public Post postProduct(final Product product, final Double price, final Integer userId, final String description);
}
