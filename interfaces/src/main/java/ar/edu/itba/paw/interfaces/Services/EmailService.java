package ar.edu.itba.paw.interfaces.Services;

public interface EmailService {

    boolean sendSimpleMessage(final String to, final String subject, final String text);

    //boolean sendSuccessfulPurchaseEmail(final String to, final String productModel, final Integer postId);

    //boolean sendSuccessfulSaleEmail(final String to, final String productModel, final Integer postId);

    boolean sendSuccessfulRegistrationEmail(final String to, final String username);

    boolean sendCodeEmail(final String to, final Integer code);

    void sendChangePasswordEmail(final String to, final String code);
}
