package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.Services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

//    private static final int PURCHASE = 0;
//    private static final int SALE = 1;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public boolean sendSimpleMessage(final String to, String subject, String text) {

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        try {
//            emailSender.send(message);
//        } catch (Exception e) {
//            return false;
//        }

        MimeMessage mimeMessage = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(text, true);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            return false;
        }

        return true;
    }

//    @Override
//    public boolean sendSuccessfulPurchaseEmail(final String to, final String productModel, final Integer postId) {
//        return sendSuccessfulTransactionEmail(to, productModel, postId, PURCHASE);
//    }
//    @Override
//    public boolean sendSuccessfulSaleEmail(final String to, final String productModel, final Integer postId) {
//        return sendSuccessfulTransactionEmail(to, productModel, postId, SALE);
//    }

    @Override
    public boolean sendSuccessfulRegistrationEmail(final String to, final String username) {
        String text = "<h2>Hi there " + username + ", we want to welcome " +
                "you to Dinomercadito!</h2>" +
                "<h3>As a registered user you'll be able to contact sellers or create posts.</h3>" +
                "<h3>Go find some products!</h3>" +
                "<p>http://pawserver.it.itba.edu.ar/paw-2018b-07/index</p>";

        boolean status = sendSimpleMessage(to, "Welcome to Dinomercadito", text);

        if (!status) {
            LOGGER.info("Unsuccessful attempt to send registration email with username {}", username);

        } else {
            LOGGER.info("Successful registration email sent with username {}", username);
        }

        return status;
    }

//    private boolean sendSuccessfulTransactionEmail(final String to, final String productModel, final Integer postId,
//                                                   final int transactionType) {
//        boolean status = sendSimpleMessage(to, "Congratulations on your " + (transactionType == PURCHASE ? "purchase" : "sale") +
//                "!", "You have successfully " + (transactionType == PURCHASE ? "purchased " : "sold ") +
//                "a brand new " + productModel + " from the post #" + postId.toString() + ".");
//
//        if (!status) {
//            LOGGER.info("Unsuccessful attempt to send transaction email with postId {}", postId);
//
//        } else {
//            LOGGER.info("Successful transaction email sent with postId = {}", postId);
//        }
//
//        return status;
//
//    }

    @Override
    public boolean sendCodeEmail(final String to, final Integer code) {

       // boolean status = sendSimpleMessage(to, "Authentication code", "Here is your authentication code: " + code);
        String text = "<h2>Here is your authentication code: "+code+"</h2>\n" +
                "<br>\n" +
                "<h3>Authentication link: http://pawserver.it.itba.edu.ar/paw-2018b-07/authentication?loggedIn=false</h3>";

        boolean status = sendSimpleMessage(to, "Authentication code", text);
        if (!status) {
            LOGGER.info("Unsuccessful attempt to send email with code {}", code);

        } else {
            LOGGER.info("Authentication email sent with code {}", code);
        }

        return status;
    }
}
