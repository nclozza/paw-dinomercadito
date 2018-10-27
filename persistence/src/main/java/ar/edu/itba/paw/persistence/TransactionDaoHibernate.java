package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.TransactionDAO;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Transaction;
import ar.edu.itba.paw.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionDaoHibernate implements TransactionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDaoHibernate.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Transaction createTransaction(Integer postId, Integer buyerUserId, Integer productQuantity, Double price, String productName) {
        User user = em.find(User.class, buyerUserId);
        Post post = em.find(Post.class, postId);
        Transaction transaction = new Transaction(post, user, productQuantity, price, productName);
        em.persist(transaction);
        LOGGER.info("Transaction inserted with transactionId = {}", transaction.getTransactionId().intValue());
        return transaction;
    }

    @Transactional
    @Override
    public boolean deleteTransactionByTransactionId(Integer transactionId) {
        Transaction transaction = em.find(Transaction.class, transactionId);

        if (transaction != null){
            em.remove(transaction);
            LOGGER.info("Transaction deleted with transactionId = {}", transactionId);

            return true;
        }
        LOGGER.info("Transaction not found with transactionId = {}", transactionId);
        return false;
    }

    @Override
    public Optional<Transaction> findTransactionByTransactionId(Integer transactionId) {
        return Optional.ofNullable(em.find(Transaction.class, transactionId));
    }

    @Override
    public List<Transaction> findTransactionsByBuyerUserId(Integer buyerUserId) {
        User user = em.find(User.class, buyerUserId);
        return user.getTransactionsList();
    }
}