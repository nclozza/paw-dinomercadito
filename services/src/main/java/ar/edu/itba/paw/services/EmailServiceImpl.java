package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.Services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public boolean sendSimpleMessage(final String to, String subject, String text) {

        MimeMessage mimeMessage = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(text, true);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean sendSuccessfulRegistrationEmail(final String to, final String username) {
        String htmlFilename = "/html/welcomeEmail.html";
        String content = transformHtmlEmailContentIntoString(htmlFilename);

        content = content.replace("$username", username);
        boolean status = sendSimpleMessage(to, "Welcome to Dinomercadito", content);

        if (!status) {
            LOGGER.info("Unsuccessful attempt to send registration email with username {}", username);

        } else {
            LOGGER.info("Successful registration email sent with username {}", username);
        }

        return status;
    }

    @Override
    public boolean sendSuccessfulPurchaseEmail(String to, String productModel, Integer postId, Integer userId) {

        String htmlFilename = "/html/successfulPurchaseEmail.html";
        String content = transformHtmlEmailContentIntoString(htmlFilename);

        content = content.replace("$productModel", productModel);
        content = content.replace("$postId", postId.toString());
        content = content.replace("$userId", userId.toString());
        boolean status = sendSimpleMessage(to, "Successful purchase", content);

        if (!status) {
            LOGGER.info("Unsuccessful attempt to send successful purchase email with postId {}", postId);

        } else {
            LOGGER.info("Successful purchase email with postId {}", postId);
        }

        return status;
    }

    @Override
    public boolean sendAnswerEmail(final String to, final String productName, final String answer) {

        String htmlFilename = "/html/answerEmail.html";
        String content = transformHtmlEmailContentIntoString(htmlFilename);

        content = content.replace("$productName", productName);
        content = content.replace("$answer", answer);
        boolean status = sendSimpleMessage(to, "Successful purchase", content);

        if (!status) {
            LOGGER.info("Unsuccessful attempt to send answer email with product name {}", productName);

        } else {
            LOGGER.info("Successful send answer email with product name {}", productName);
        }

        return status;
    }

    @Override
    public boolean sendChangePostStatusEmail(final String to, final String productName, final String description,
                                             final String newStatus) {

        String htmlFilename = "/html/changePostStatusEmail.html";
        String content = transformHtmlEmailContentIntoString(htmlFilename);

        content = content.replace("$productName", productName);
        content = content.replace("$description", description);
        content = content.replace("$newStatus", newStatus);
        boolean status = sendSimpleMessage(to, "Successful purchase", content);

        if (!status) {
            LOGGER.info("Unsuccessful attempt to send change status email with product name {}", productName);

        } else {
            LOGGER.info("Successful send change status email with product name {}", productName);
        }

        return status;
    }

    @Override
    public boolean sendCodeEmail(final String to, final Integer code) {
        String htmlFilename = "/html/authCodeEmail.html";
        String content = transformHtmlEmailContentIntoString(htmlFilename);

        content = content.replace("$code", code.toString());
        boolean couldSendMessage = sendSimpleMessage(to, "Authentication code", content);

        if (!couldSendMessage)
            LOGGER.info("Unsuccessful attempt to send email with code {}", code);
        else
            LOGGER.info("Authentication email sent with code {}", code);

        return couldSendMessage;
    }

    @Override
    public boolean sendChangePasswordEmail(final String to, final String code) {

        String htmlFilename = "/html/changePasswordEmail.html";
        String content = transformHtmlEmailContentIntoString(htmlFilename);
        content = content.replace("$code", code);
        boolean couldSendMessage = sendSimpleMessage(to, "Change your password", content);

        if (!couldSendMessage)
            LOGGER.info("Unsuccessful attempt to send email with code {}", code);
        else
            LOGGER.info("Authentication email sent with code {}", code);

        return couldSendMessage;
    }

    @Override
    public boolean sendPurchaseEmail(final String to, final String buyerUsername, final String buyerEmail,
                                     final String buyerPhoneNumber, final String productName,
                                     final String postDescription, final Integer productQuantity) {

        String htmlFilename = "/html/purchaseEmail.html";
        String content = transformHtmlEmailContentIntoString(htmlFilename);
        content = content.replace("$buyerUsername", buyerUsername);
        content = content.replace("$buyerEmail", buyerEmail);
        content = content.replace("$buyerPhoneNumber", buyerPhoneNumber);
        content = content.replace("$productName", productName);
        content = content.replace("$postDescription", postDescription);
        content = content.replace("$productQuantity", productQuantity.toString());
        boolean couldSendMessage = sendSimpleMessage(to, "Change your password", content);

        if (!couldSendMessage)
            LOGGER.info("Unsuccessful attempt to send email with buyer username {}", buyerUsername);
        else
            LOGGER.info("Authentication email sent with buyer username {}", buyerUsername);

        return couldSendMessage;
    }

    @Override
    public boolean sendDeclinedTransaction(final String to, final String buyerUsername, final String productName) {

        String htmlFilename = "/html/declinedTransactionEmail.html";
        String content = transformHtmlEmailContentIntoString(htmlFilename);
        content = content.replace("$buyerUsername", buyerUsername);
        content = content.replace("$productName", productName);
        boolean couldSendMessage = sendSimpleMessage(to, "Declined transaction", content);

        if (!couldSendMessage)
            LOGGER.info("Unsuccessful attempt to send email with declined transaction by {}", buyerUsername);
        else
            LOGGER.info("Authentication email sent with declined transaction by {}", buyerUsername);

        return couldSendMessage;
    }

    @Override
    public boolean sendAskEmail(final String to, final String productName, final String question,
                                final Integer questionId) {

        String htmlFilename = "/html/askEmail.html";
        String content = transformHtmlEmailContentIntoString(htmlFilename);
        content = content.replace("$productName", productName);
        content = content.replace("$questionMessage", question);
        content = content.replace("$questionId", questionId.toString());
        boolean couldSendMessage = sendSimpleMessage(to, "Question asked", content);

        if (!couldSendMessage)
            LOGGER.info("Unsuccessful attempt to send email with questionId {}", questionId);
        else
            LOGGER.info("Authentication email sent with questionId {}", questionId);

        return couldSendMessage;
    }

    @Override
    public boolean sendReviewEmail(final String to, final String buyerUsername, final String description,
                                   final Integer rating) {

        String htmlFilename = "/html/reviewEmail.html";
        String content = transformHtmlEmailContentIntoString(htmlFilename);
        content = content.replace("$buyerUsername", buyerUsername);
        content = content.replace("$description", description);
        content = content.replace("$rating", rating.toString());

        boolean couldSendMessage = sendSimpleMessage(to, "Review made", content);

        if (!couldSendMessage)
            LOGGER.info("Unsuccessful attempt to send email with review made by {}", buyerUsername);
        else
            LOGGER.info("Authentication email sent with review made by {}", buyerUsername);

        return couldSendMessage;
    }

    private String transformHtmlEmailContentIntoString(final String htmlFilename) {
        StringBuilder contentBuilder = new StringBuilder();
        InputStream inputStream = EmailServiceImpl.class.getResourceAsStream(htmlFilename);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        String str;

        try {
            while ((str = reader.readLine()) != null)
                contentBuilder.append(str);

            reader.close();
        } catch (IOException e) {
            LOGGER.info("Unsuccessful attempt to read {}", htmlFilename);
        }

        return contentBuilder.toString();
    }
}
