package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.UserNotAuthenticated;

public interface UserNotAuthenticatedService {

    UserNotAuthenticated createUser(final String username, final String password, final String email,
                                    final String phone, final String birthdate, final String signUpDate,
                                    final Integer code);

    UserNotAuthenticated findUserByUserId(final Integer userId);

    boolean deleteUser(final Integer userId);

    UserNotAuthenticated findUserByUsername(final String username);

    UserNotAuthenticated findUserByCode(final Integer code);

    Integer generateCode();

    boolean checkUsername(final String username);
}
