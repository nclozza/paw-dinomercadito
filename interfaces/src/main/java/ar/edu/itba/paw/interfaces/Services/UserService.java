package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

import java.time.LocalDate;

public interface UserService {

    /**
     * Create a new user.
     *
     * @param username The name of the user.
     * @return The created user.
     */
    public User createUserWithAddress(final String username, final String password, final String email, final String phone,
                           final LocalDate birthdate, final String street, final Integer number, final String city,
                           final String province, final String zipCode, final String country);

    public User createUser(final String username, final String password, final String email, final String phone,
                           final LocalDate birthdate);

    public User findUserById(final Integer userId);

    public boolean deleteUser(final Integer userId);

    public boolean updateUserFunds(final Integer userId, final Double funds);

    public boolean buyProduct(final Integer buyerId, final Integer sellerId, final Integer postId);

    public boolean postProduct(final Integer productId, final Double price, final Integer userId,
                               final String description);
}
