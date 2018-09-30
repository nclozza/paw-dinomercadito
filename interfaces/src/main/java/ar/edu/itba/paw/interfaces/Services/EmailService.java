package ar.edu.itba.paw.interfaces.Services;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
    public void sendSuccessfulPurchaseEmail(final String to, final String productModel, final Integer postId);
    public void sendSuccesfulSaleEmail(final String to, final String productModel, final Integer postId);
    public void sendSuccessfulRegistrationEmail(final String to, final String username);
    public void sendCodeEmail(final String to, final Integer code);
}
