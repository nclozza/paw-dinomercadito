package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.User;

public interface UserDAO {

    /**
     * Create a new user.
     *
     * @param username The user's username.
     * @return The created user.
     */
    public User createUser(final String username, final String password, final String email, final String phone,
                           final String birthdate, final Double funds);

    public User findUserByUserId(final Integer userId);

    public boolean deleteUser(final Integer userId);

    public User updateUser(final Integer userId, final String password, final String email,
                              final String phone, final String birthdate, final Double funds);

    public User findUserByUsername(String username);

    public boolean checkUsername(final String username);

    public boolean addFundsToUserId(final Double funds, final Integer userId);
}
