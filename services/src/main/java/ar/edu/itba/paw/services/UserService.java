package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.User;

public interface UserService {
    User getUser(String username);
    boolean createUser(User user);
    boolean deleteUser(String username);
    boolean updateUser(User user);
}
