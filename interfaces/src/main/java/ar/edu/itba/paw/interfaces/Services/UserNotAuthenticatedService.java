package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.UserNotAuthenticated;

public interface UserNotAuthenticatedService {

    public UserNotAuthenticated createUser(final String username, final String password, final String email, final String phone,
                           final String birthdate, final String signUpDate, final Integer code);

    public UserNotAuthenticated findUserByUserId(final Integer userId);

    public boolean deleteUser(final Integer userId);

    public UserNotAuthenticated findUserByUsername(String username);
}
