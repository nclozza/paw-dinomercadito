package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    Transaction createTransaction(final Integer postId, final Integer buyerUserId, final Integer visits,
                                  final Double price, final String productName);

    boolean deleteTransactionByTransactionId(final Integer transactionId);

    Optional<Transaction> findTransactionByTransactionId(final Integer transactionId);

    List<Transaction> findTransactionsByBuyerUserId(final Integer buyerUserId);

    Integer makeTransaction(final Integer buyerUserId, final Integer postId, final Integer visits);

    Optional<Transaction> changeTransactionStatus(Integer transactionId, String status);
}

