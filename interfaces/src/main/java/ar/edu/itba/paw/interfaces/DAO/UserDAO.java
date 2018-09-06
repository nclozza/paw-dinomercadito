package ar.edu.itba.paw.interfaces.DAO;

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
                           final Integer addressId, final LocalDate birthdate);
    public User findUserByUserId(final Integer userId);
    public boolean deleteUser(final Integer userId);
    public boolean updateUserFunds(final Integer userId, final Double funds);
}
