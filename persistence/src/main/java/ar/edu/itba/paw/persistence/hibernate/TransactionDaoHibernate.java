package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.DAO.TransactionDAO;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Transaction;
import ar.edu.itba.paw.models.User;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    @Transactional
    @Override
    public List<Transaction> findTransactionsByBuyerUserId(Integer buyerUserId) {
        User user = em.find(User.class, buyerUserId);
        Hibernate.initialize(user.getTransactionsList());
        return user.getTransactionsList();
    }

    @Transactional
    @Override
    public Optional<Transaction> changeTransactionStatus(Integer transactionId, String status){
        Transaction transaction = em.find(Transaction.class, transactionId);

        if(transaction != null){
            transaction.setStatus(status);
            em.merge(transaction);
            LOGGER.info("Transaction updated with transactionId = {}", transactionId);
        }else {
            LOGGER.info("Transaction not found with transactionId = {}", transactionId);
        }

        return Optional.ofNullable(transaction);
    }

    @Override
    public List<Transaction> findTransactionsByUserIdAndPostId(final Integer userId, final Integer postId){
        final TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t " +
                "WHERE t.postBought.postId = :postId " +
                "AND t.buyerUser.userId = :userId", Transaction.class);

        query.setParameter("postId", postId);
        query.setParameter("userId", userId);
        query.setMaxResults(1);

        return query.getResultList();
    }

    @Override
    public List<Transaction> findPurchasesByUserIdAndStatus(final Integer userId, final String status){
        final TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t " +
                "WHERE t.buyerUser.userId = :userId " +
                "AND t.status = :status", Transaction.class);

        query.setParameter("userId", userId);
        query.setParameter("status", status);

        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Transaction> findSalesByUserIdAndStatus(final Integer userId, final String status){
        final TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t " +
                "INNER JOIN Post p " +
                "ON p.postId = t.postBought.postId " +
                "WHERE p.userSeller.userId = :userId " +
                "AND t.status = :status", Transaction.class);

        query.setParameter("userId", userId);
        query.setParameter("status", status);

        return query.getResultList();
    }

    @Override
    public Boolean findPendingTransaction(Integer postId, Integer buyerUserId){
        final TypedQuery<Transaction> query = em.createQuery("FROM Transaction t " +
                "WHERE t.postBuyed.postId = :postId " +
                "AND t.buyerUser.userId = :userId " +
                "AND t.status = 'Pending'", Transaction.class);
        query.setParameter("userId", buyerUserId);
        query.setParameter("postId", postId);
        List<Transaction> list = query.getResultList();

        return list.isEmpty()? false : true;
    }
}
