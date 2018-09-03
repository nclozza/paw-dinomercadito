package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

import java.util.Date;

public interface UserService {
    User findUserByUsername(String username);

    /**
     * Create a new user.
     *
     * @param username The name of the user.
     * @return The created user.
     */
    User createUser(String username, String password, String email, String phone, String address, Date birthdate);

//    User getUser(String username);
//    boolean deleteUser(String username);
//    boolean updateUser(User user);
}
