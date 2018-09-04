package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.UserDAO;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public User findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public User createUser(final String username, final String password, final String email, final String phone, final String address, final LocalDate birthdate) {
        return userDAO.createUser(username, password, email, phone, address, birthdate);
    }

}
