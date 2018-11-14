package ar.edu.itba.paw.interfaces.Services;

public interface EmailService {

    boolean sendSimpleMessage(final String to, final String subject, final String text);

    
    boolean sendCodeEmail(final String to, final Integer code);

    boolean sendChangePasswordEmail(final String to, final String code);

    boolean sendPurchaseEmail(final String to, final String buyerUsername, final String buyerEmail,
                              final String buyerPhoneNumber, final String productName, final String postDescription,
                              final Integer productQuantity);

    boolean sendDeclinedTransaction(final String to, final String buyerUsername, final String productName);

    boolean sendAskEmail(final String to, final String productName, final String question, final Integer questionId);

    boolean sendReviewEmail(final String to, final String buyerUsername, final String description, final Integer rating);

    boolean sendSuccessfulRegistrationEmail(final String to, final String username);

    boolean sendSuccessfulPurchaseEmail(final String to, final String productModel, final Integer postId,
                                        final Integer userId);

    boolean sendAnswerEmail(final String to, final String productName, final String answer);
}
