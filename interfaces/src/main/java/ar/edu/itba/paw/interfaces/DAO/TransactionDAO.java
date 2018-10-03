package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionDAO {

    Transaction createTransaction(final Integer postId, final Integer buyerUserId, final Integer productQuantity,
                                  final Double price, final String productName);

    boolean deleteTransactionByTransactionId(final Integer transactionId);

    Optional<Transaction> findTransactionByTransactionId(final Integer transactionId);

    List<Transaction> findTransactionsByBuyerUserId(final Integer buyerUserId);
}
