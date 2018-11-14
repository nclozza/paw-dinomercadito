package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.ForgotPassword;
import ar.edu.itba.paw.models.User;

import java.util.Optional;

public interface ForgotPasswordService {

    ForgotPassword createNewRequest(User user, String requestDate);

    Optional<User> findUserByCode(String code);

    Boolean checkCodeDoesNotExist(String code);

    void deleteRequestById(Integer forgetPasswordId);

    String randomStringGenerator();

    String encrypt(String strClearText);

    Optional<ForgotPassword> findRequestByCode(String code);
}
