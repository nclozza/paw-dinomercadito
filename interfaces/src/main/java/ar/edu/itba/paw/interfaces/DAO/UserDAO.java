package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.User;

import java.util.Optional;

public interface UserDAO {

    /**
     * Creates a new user POJO and stores it in the database.
     * @param username The user's username as a String.
     * @param password The user's password as a String.
     * @param email The user's email as a String.
     * @param phone The user's phone as a String.
     * @param birthdate The user's birthdate as a String.
     * @param funds The user's funds as a Double.
     * @return A new user POJO. This method will never return null.
     */
    User createUser(final String username, final String password, final String email, final String phone,
                    final String birthdate, final Double funds);

    /**
     * Finds and retrieves a user from the database through its specified ID.
     * @param userId The user's unique ID as an Integer.
     * @return An Optional object that contains either the user POJO if the user was successfully found and retrieved
     *      from the database, or an empty Optional otherwise.
     */
    Optional<User> findUserByUserId(final Integer userId);

    boolean deleteUser(final Integer userId);

    /**
     * Updates a user's information in the database. Every user attribute can be updated except for its unique ID and
     * its username.
     * @param userId The user's unique ID as an Integer.
     * @param password The user's supposedly new password passed as a String.
     * @param email The user's supposedly new email passed as a String.
     * @param phone The user's supposedly new phone number passed as a String.
     * @param birthdate The user's supposedly new birthdate passed as a String.
     * @param funds The user's supposedly new funds passed as a Double.
     * @return An Optional object that contains either the user POJO if the user was successfully found and updated
     *      in the database, or an empty Optional otherwise.
     */
    Optional<User> updateUser(final Integer userId, final String password, final String email,
                    final String phone, final String birthdate, final Double funds);

    /**
     * Finds and retrieves a user through its specified username, from the database.
     * @param username The user's username passed as a String.
     * @return An Optional object that contains either the user POJO if the user was successfully found and retrieved
     *      from the database, or an empty Optional otherwise.
     */
    Optional<User> findUserByUsername(final String username);

    boolean checkUsername(final String username);

    boolean addFundsToUserId(final Double funds, final Integer userId);
}
