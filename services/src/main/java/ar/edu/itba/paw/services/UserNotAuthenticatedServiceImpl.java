package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.UserNotAuthenticatedDAO;
import ar.edu.itba.paw.interfaces.Services.UserNotAuthenticatedService;
import ar.edu.itba.paw.models.UserNotAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Transactional
@Service
public class UserNotAuthenticatedServiceImpl implements UserNotAuthenticatedService {
    @Autowired
    private UserNotAuthenticatedDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserNotAuthenticated createUser(final String username, final String password, final String email,
                                           final String phone, final String birthdate, final String signUpDate,
                                           final Integer code) {
        return userDAO.createUser(username, passwordEncoder.encode(password), email, phone, birthdate, signUpDate, code);
    }

    @Override
    public boolean deleteUser(final Integer userId) {
        return userDAO.deleteUser(userId);
    }

    @Transactional (readOnly = true)
    @Override
    public Optional<UserNotAuthenticated> findUserByUsername(final String username) {
        return userDAO.findUserByUsername(username);
    }

    @Transactional (readOnly = true)
    @Override
    public Optional<UserNotAuthenticated> findUserByUserId(final Integer userId) {
        return userDAO.findUserByUserId(userId);
    }

    @Transactional (readOnly = true)
    @Override
    public Optional<UserNotAuthenticated> findUserByCode(final Integer code) {
        return userDAO.findUserByCode(code);
    }

    @Override
    public Integer generateCode() {
        boolean check = true;
        Integer code;

        do {
            Random rand = new Random();
            code = rand.nextInt(900000) + 100000;

            if (userDAO.checkCodeDoesNotExist(code))
                check = false;
        } while (check);

        return code;
    }

    @Override
    public boolean checkUsername(final String username) {
        return userDAO.checkUsername(username);
    }

    @Override
    public Optional<UserNotAuthenticated> findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Override
    public boolean checkEmail(String email) {
        return userDAO.checkEmail(email);
    }
}
