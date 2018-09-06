package ar.edu.itba.paw.interfaces.Services;

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
                           final Integer addressId, final LocalDate birthdate, final String street,
                           final Integer number,final String city, final String province, final Integer zipCode,
                           final String country);
    public User findUserByUserId(final Integer userId);
    public boolean deleteUser(final Integer userId);
    public boolean updateUserFunds(final Integer userId, final Double funds);
    public boolean buyProduct(final Integer buyerId, final Integer sellerId, final Integer postId);
    public boolean postProduct(final Integer productId, final Double price, final Integer userId, final String description);
}
