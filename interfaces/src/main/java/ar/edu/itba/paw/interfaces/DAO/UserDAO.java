package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.User;

public interface UserDAO {

    /**
     * Create a new user.
     *
     * @param username The user's username.
     * @return The created user.
     */
    User createUser(final String username, final String password, final String email, final String phone,
                    final String birthdate, final Double funds);

    User findUserByUserId(final Integer userId);

    boolean deleteUser(final Integer userId);

    User updateUser(final Integer userId, final String password, final String email,
                    final String phone, final String birthdate, final Double funds);

    User findUserByUsername(final String username);

    boolean checkUsername(final String username);

    boolean addFundsToUserId(final Double funds, final Integer userId);
}
