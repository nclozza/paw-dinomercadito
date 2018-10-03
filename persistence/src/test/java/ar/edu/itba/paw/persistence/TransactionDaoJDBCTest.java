package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.TransactionDAO;
import ar.edu.itba.paw.models.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:testTransactions.sql")
public class TransactionDaoJDBCTest {

    private final static int POST_ID = 700;
    private final static int BUYER_USER_ID = 300;
    private final static int PRODUCT_QUANTITY = 10;
    private final static double PRICE = 580.00;
    private final static String PRODUCT_NAME = "OnePlus 6T";

    private static final int ROWS_PRE_INSERTED = 2;
    private static final int DUMMY_TRANSACTION_ID = 6000;


    @Autowired
    private DataSource ds;

    @Autowired
    private TransactionDAO transactionDAO;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @After
    public void cleanTables() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "transactions", "posts",
                "products", "users");
    }

    @Test
    public void testCreateTransaction() {
        final Transaction transaction = transactionDAO.createTransaction(POST_ID, BUYER_USER_ID, PRODUCT_QUANTITY,
                PRICE, PRODUCT_NAME);

        assertNotNull(transaction);
        assertEquals(POST_ID, transaction.getPostId().intValue());
        assertEquals(BUYER_USER_ID, transaction.getBuyerUserId().intValue());
        assertEquals(PRODUCT_QUANTITY, transaction.getProductQuantity().intValue());
        assertEquals(PRICE, transaction.getPrice(), 0.001);
        assertEquals(PRODUCT_NAME, transaction.getProductName());
        assertEquals(ROWS_PRE_INSERTED + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "transactions"));
    }

    @Test
    public void testDeleteTransactionByTransactionId() {
        assertTrue(transactionDAO.deleteTransactionByTransactionId(DUMMY_TRANSACTION_ID));
        assertEquals(ROWS_PRE_INSERTED - 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "transactions"));
    }

    @Test
    public void testFindTransactionByTransactionId() {
        final Optional<Transaction> transaction = transactionDAO.findTransactionByTransactionId(DUMMY_TRANSACTION_ID);

        assertTrue(transaction.isPresent());
        assertEquals(DUMMY_TRANSACTION_ID, transaction.get().getTransactionId().intValue());
    }
}
