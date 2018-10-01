package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionDAO {

    public Transaction createTransaction(final Integer postId, final Integer buyerUserId, final Integer productQuantity,
                                         final Double price, final String productName);

    public boolean deleteTransaction(final Integer transactionId);

    public Optional<Transaction> findTransactionByTransactionId(final Integer transactionId);

    public List<Transaction> findTransactionsByBuyerUserId(final Integer buyerUserId);
}
