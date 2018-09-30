package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.UserNotAuthenticatedDAO;
import ar.edu.itba.paw.interfaces.Services.UserNotAuthenticatedService;
import ar.edu.itba.paw.models.UserNotAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class UserNotAuthenticatedServiceImpl implements UserNotAuthenticatedService {
    @Autowired
    private UserNotAuthenticatedDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserNotAuthenticated createUser(final String username, final String password, final String email, final String phone,
                                           final String birthdate, final String signUpDate, final Integer code) {

        return userDAO.createUser(username, passwordEncoder.encode(password), email, phone, birthdate, signUpDate, code);
    }

    public UserNotAuthenticated findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    public UserNotAuthenticated findUserByUserId(final Integer userId) {
        return userDAO.findUserByUserId(userId);
    }

    public boolean deleteUser(final Integer userId) { return userDAO.deleteUser(userId);};

    @Override
    public UserNotAuthenticated findUserByCode(final Integer code) { return userDAO.findUserByCode(code); }

    public Integer generateCode() {

        boolean check = true;
        Integer code;

        do {
            Random rand = new Random();
            code = rand.nextInt(900000) + 100000;

            if (userDAO.checkCode(code))
                check = false;
        } while(check);

        return code;
    }

    public boolean checkUsername (final String username){ return userDAO.checkUsername(username); }
}
