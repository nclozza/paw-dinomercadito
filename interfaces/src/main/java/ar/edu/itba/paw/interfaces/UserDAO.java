package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

import java.time.LocalDate;

public interface UserDAO {

    User findUserByUsername(String username);

    /**
     * Create a new user.
     *
     * @param username The user.
     * @return The created user.
     */
    User createUser(String username, String password, String email, String phone, String address, LocalDate birthdate);
}
