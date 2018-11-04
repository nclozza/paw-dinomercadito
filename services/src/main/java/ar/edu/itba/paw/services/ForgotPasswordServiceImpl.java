package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.ForgotPasswordDAO;
import ar.edu.itba.paw.interfaces.Services.ForgotPasswordService;
import ar.edu.itba.paw.models.ForgotPassword;
import ar.edu.itba.paw.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private ForgotPasswordDAO forgotPasswordDAO;

    @Autowired
    private EmailServiceImpl emailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ForgotPasswordServiceImpl.class);

    @Override
    public ForgotPassword createNewRequest(User user, String requestDate) {
        boolean check = true;
        String stringToEncrypt = "";
        Integer encryptedString = 0;

        do{
            stringToEncrypt = randomStringGenerator();
            encryptedString = stringToEncrypt.hashCode();

            if(forgotPasswordDAO.checkCode(encryptedString.toString()))
                check = false;
        } while (check);

        emailService.sendChangePasswordEmail(user.getEmail(), stringToEncrypt);

        return forgotPasswordDAO.createNewRequest(user.getUserId(), requestDate, encryptedString.toString());
    }

    @Override
    public Optional<User> findUserByCode(String code) {
        return forgotPasswordDAO. findUserByCode(code);
    }

    @Override
    public Boolean checkCode(String code) {
        return forgotPasswordDAO.checkCode(code);
    }

    @Override
    public void deleteRequestById(Integer forgetPasswordId) {
        forgotPasswordDAO.deleteRequestById(forgetPasswordId);
    }

    @Override
    public String randomStringGenerator() {
        String string = UUID.randomUUID().toString();

        return string;
    }

    @Override
    public String encrypt(String strClearText){
        String strData = "";
        String strKey = "Dinomercadito";
        try {
            SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(),"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            byte[] encrypted = cipher.doFinal(strClearText.getBytes());
            strData = new String(encrypted);

        } catch (Exception e) {
            LOGGER.error("Can't encrypt message for ForgetPassword");
        }
        return strData;
    }

    @Override
    public Optional<ForgotPassword> findRequestByCode(String code) {
        return forgotPasswordDAO.findRequestByCode(code);
    }
}
