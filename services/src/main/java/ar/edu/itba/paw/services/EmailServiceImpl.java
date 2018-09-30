package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    private static final int PURCHASE = 0;
    private static final int SALE = 1;

    @Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMessage(final String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendSuccessfulPurchaseEmail(final String to, final String productModel, final Integer postId) {
        sendSuccessfulTransactionEmail(to, productModel, postId, PURCHASE);
    }

    public void sendSuccesfulSaleEmail(final String to, final String productModel, final Integer postId) {
        sendSuccessfulTransactionEmail(to, productModel, postId, SALE);
    }

    public void sendSuccessfulRegistrationEmail(final String to, final String username) {
        sendSimpleMessage(to, "Welcome to Dinomercadito", "Hi there " + username + ", we want to welcome " +
                "you to Dinomercadito! As a registered user you'll be able to purchase and/or post products.");
    }

    private void sendSuccessfulTransactionEmail(final String to, final String productModel, final Integer postId,
                                                final int transactionType) {
        sendSimpleMessage(to, "Congratulations on your " + (transactionType == PURCHASE? "purchase" : "sale") +
                        "!", "You have successfully " + (transactionType == PURCHASE? "purchased " : "sold ") +
                        "a brand new " + productModel + " from the post #" + postId.toString() + ".");
    }

    public void sendCodeEmail(final String to, final Integer code){

        sendSimpleMessage(to, "Authentication code", "Here is your authentication code: " + code);
    }
}
