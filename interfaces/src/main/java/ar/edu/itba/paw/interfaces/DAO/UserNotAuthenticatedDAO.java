package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.UserNotAuthenticated;

import java.util.Optional;

public interface UserNotAuthenticatedDAO {

    UserNotAuthenticated createUser(final String username, final String password, final String email,
                                    final String phone, final String birthdate, final String signUpDate,
                                    final Integer code);

    Optional<UserNotAuthenticated> findUserByUserId(final Integer userId);

    boolean deleteUser(final Integer userId);

    Optional<UserNotAuthenticated> findUserByUsername(String username);

    Optional<UserNotAuthenticated> findUserByCode(final Integer code);

    boolean checkCode(final Integer code);

    boolean checkUsername(final String username);
}
