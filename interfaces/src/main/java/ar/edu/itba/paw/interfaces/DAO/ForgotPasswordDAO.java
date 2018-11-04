package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.ForgotPassword;
import ar.edu.itba.paw.models.User;

import java.util.Optional;

public interface ForgotPasswordDAO {

    ForgotPassword createNewRequest(Integer userId, String requestDate, String code);

    Optional<User> findUserByCode(String code);

    Boolean checkCode(String code);

    void deleteRequestById(Integer forgetPasswordId);

    Optional<ForgotPassword> findRequestByCode(String code);
}
