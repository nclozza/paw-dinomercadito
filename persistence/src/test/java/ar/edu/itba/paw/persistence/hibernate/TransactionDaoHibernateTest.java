package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:testTransactions.sql")
public class TransactionDaoHibernateTest {

    private final static int POST_ID = 700;
    private final static int BUYER_USER_ID = 300;
    private final static int PRODUCT_QUANTITY = 10;
    private final static double PRICE = 580.00;
    private final static String PRODUCT_NAME = "OnePlus 6T";
    private final static String STATUS = "Confirmed";

    private static final int DUMMY_TRANSACTION_ID = 6000;


    @Autowired
    private TransactionDaoHibernate transactionDAO;

    @PersistenceContext
    EntityManager em;

    @Before
    public void setUp() {
        em.createNativeQuery("DELETE FROM transactions");
        em.flush();
    }

    @Test
    public void testCreateTransaction() {
        final Transaction transaction = transactionDAO.createTransaction(POST_ID, BUYER_USER_ID, PRODUCT_QUANTITY,
                PRICE, PRODUCT_NAME);

        assertNotNull(transaction);
        assertEquals(POST_ID, transaction.getBoughtPost().getPostId().intValue());
        assertEquals(BUYER_USER_ID, transaction.getBuyerUser().getUserId().intValue());
        assertEquals(PRODUCT_QUANTITY, transaction.getProductQuantity().intValue());
        assertEquals(PRICE, transaction.getPrice(), 0.001);
        assertEquals(PRODUCT_NAME, transaction.getProductName());
        assertTrue(em.contains(transaction));
    }

    @Test
    public void testDeleteTransactionByTransactionId() {
        assertTrue(transactionDAO.deleteTransactionByTransactionId(DUMMY_TRANSACTION_ID));
    }

    @Test
    public void testFindTransactionByTransactionId() {
        final Optional<Transaction> transaction = transactionDAO.findTransactionByTransactionId(DUMMY_TRANSACTION_ID);

        assertTrue(transaction.isPresent());
        assertEquals(DUMMY_TRANSACTION_ID, transaction.get().getTransactionId().intValue());
    }

    @Test
    public void testFindTransactionsByBuyerUserId() {
        final List<Transaction> transactionsList = transactionDAO.findTransactionsByBuyerUserId(BUYER_USER_ID);

        assertFalse(transactionsList.isEmpty());

        for (Transaction t : transactionsList)
            assertEquals(BUYER_USER_ID, t.getBuyerUser().getUserId().intValue());
    }

    @Test
    public void testFindPurchasesByUserIdAndStatus() {
        final List<Transaction> transactionsList = transactionDAO.findPurchasesByUserIdAndStatus(BUYER_USER_ID, STATUS);

        assertFalse(transactionsList.isEmpty());

        for (Transaction t : transactionsList) {
            assertEquals(BUYER_USER_ID, t.getBuyerUser().getUserId().intValue());
            assertEquals(STATUS, t.getStatus());
        }
    }

    @Test
    public void testFindSalesByUserIdAndStatus() {
        final List<Transaction> transactionsList = transactionDAO.findSalesByUserIdAndStatus(BUYER_USER_ID, STATUS);

        assertFalse(transactionsList.isEmpty());

        for (Transaction t : transactionsList) {
            assertEquals(BUYER_USER_ID, t.getBuyerUser().getUserId().intValue());
            assertEquals(STATUS, t.getStatus());
        }
    }
}
