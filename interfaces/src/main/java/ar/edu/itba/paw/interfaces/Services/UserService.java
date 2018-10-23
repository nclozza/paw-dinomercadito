package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.User;

import java.util.Optional;

public interface UserService {

    /**
     * Create a new user.
     *
     * @param username The name of the user.
     * @return The created user.
     */
    User createUserWithAddress(final String username, final String password, final String email,
                               final String phone, final String birthdate, final String street,
                               final Integer number, final String city, final String province,
                               final String zipCode, final String country);

    User createUser(final String username, final String password, final String email, final String phone,
                    final String birthdate);

    User createUser(final String username, final String password, final String email, final String phone,
                    final String birthdate, final Double funds);

    Optional<User> findUserByUserId(final Integer userId);

    boolean deleteUser(final Integer userId);

    Optional<User> updateUser(final Integer userId, final String password, final String email,
                    final String phone, final String birthdate, final Double funds);

    boolean postProduct(final Integer productId, final Double price, final Integer userId,
                        final String description, final Integer visits);

    Optional<User> findUserByUsername(final String username);

    Optional<User> updateUserWithoutPasswordEncoder(final Integer userId, final String password, final String email,
                                          final String phone, final String birthdate, final Double funds);

    boolean checkUsername(final String username);

    boolean addFundsToUserId(final Double funds, final Integer userId);
}
