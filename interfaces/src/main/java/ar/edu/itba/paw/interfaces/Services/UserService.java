package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.User;

public interface UserService {

    /**
     * Create a new user.
     *
     * @param username The name of the user.
     * @return The created user.
     */
    public User createUserWithAddress(final String username, final String password, final String email,
                                      final String phone, final String birthdate, final String street,
                                      final Integer number, final String city, final String province,
                                      final String zipCode, final String country);

    public User createUser(final String username, final String password, final String email, final String phone,
                           final String birthdate);

    public User createUser(final String username, final String password, final String email, final String phone,
                           final String birthdate, final Double funds);

    public User findUserByUserId(final Integer userId);

    public boolean deleteUser(final Integer userId);

    public User updateUser(final Integer userId, final String password, final String email,
                              final String phone, final String birthdate, final Double funds);

    public boolean postProduct(final Integer productId, final Double price, final Integer userId,
                               final String description, final Integer productQuantity);
}
