package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionDAO {

    /**
     * Creates a new transaction POJO and stores it in the database. A Transaction acts as a record of a transaction
     * made between two users over a certain post.
     * @param postId The post's unique ID as an Integer to associate the transaction with said post. The post contains a
     *               reference to the seller's user ID and therefore its information.
     * @param buyerUserId The buyer's unique user ID as an Integer.
     * @param visits The visit quantity that the post has from a user, as an Integer.
     * @param price The post's price set by the seller, as an Integer.
     * @param productName The product's model name as a String.
     * @return A new transaction POJO. This method will never return null.
     */
    Transaction createTransaction(final Integer postId, final Integer buyerUserId, final Integer visits,
                                  final Double price, final String productName);

    /**
     * Deletes the transaction specified by its ID from the database.
     * @param transactionId The transaction's unique ID as an Integer to find and delete the transaction.
     * @return True if the Transaction was successfully deleted from the database, false otherwise.
     */
    boolean deleteTransactionByTransactionId(final Integer transactionId);

    /**
     * Finds and retrieves the transaction specified by its ID, from the database.
     * @param transactionId The transaction's ID as an Integer.
     * @return An Optional object that contains either the transaction POJO if the transaction was successfully found
     *      and retrieved from the database, or an empty Optional otherwise.
     */
    Optional<Transaction> findTransactionByTransactionId(final Integer transactionId);

    /**
     * Finds and retrieves a List of all the transactions made by the specified user as a buyer, from the database.
     * @param buyerUserId The buyer's unique ID as an Integer.
     * @return A List with all the transactions made by the specified user, or an empty list if said user never bought
     *      anything yet.
     */
    List<Transaction> findTransactionsByBuyerUserId(final Integer buyerUserId);

    Optional<Transaction> changeTransactionStatus(Integer transactionId, String status);

    List<Transaction> findTransactionsByUserIdAndPostId(final Integer userId, final Integer postId);

    List<Transaction> findPurchasesByUserIdAndStatus(final Integer userId, final String status);

    List<Transaction> findSalesByUserIdAndStatus(final Integer userId, final String status);

    Boolean hasNoPendingTransaction(Integer postId, Integer buyerUserId);
}
