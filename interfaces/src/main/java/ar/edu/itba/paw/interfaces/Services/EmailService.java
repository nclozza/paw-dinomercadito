package ar.edu.itba.paw.interfaces.Services;

public interface EmailService {

    void sendSimpleMessage(final String to, final String subject, final String text);

    void sendSuccessfulPurchaseEmail(final String to, final String productModel, final Integer postId);

    void sendSuccessfulSaleEmail(final String to, final String productModel, final Integer postId);

    void sendSuccessfulRegistrationEmail(final String to, final String username);

    void sendCodeEmail(final String to, final Integer code);
}
