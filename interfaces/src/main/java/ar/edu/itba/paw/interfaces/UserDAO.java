package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

import java.util.Date;

public interface UserDAO {

    User findUserByUsername(String username);

    /**
     * Create a new user.
     *
     * @param user The user.
     * @return The created user.
     */
    User createUser(String username, String password, String email, String phone, String address, Date birthdate);
}
