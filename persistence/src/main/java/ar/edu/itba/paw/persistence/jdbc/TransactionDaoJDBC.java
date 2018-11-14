package ar.edu.itba.paw.persistence.jdbc;

import ar.edu.itba.paw.interfaces.DAO.TransactionDAO;
import ar.edu.itba.paw.models.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TransactionDaoJDBC implements TransactionDAO {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDaoJDBC.class);

    @Autowired
    public TransactionDaoJDBC(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("transactions")
                .usingGeneratedKeyColumns("transactionid");
    }

    private final static RowMapper<Transaction> ROW_MAPPER = (resultSet, rowNum) -> new Transaction(
            resultSet.getInt("transactionid"),
            resultSet.getInt("postid"),
            resultSet.getInt("buyeruserid"),
            resultSet.getInt("productquantity"),
            resultSet.getDouble("price"),
            resultSet.getString("productname")
    );

    @Override
    public Transaction createTransaction(final Integer postId, final Integer buyerUserId, final Integer productQuantity,
                                         final Double price, final String productName) {
        final Map<String, Object> args = new HashMap<>();
        args.put("postid", postId);
        args.put("buyeruserid", buyerUserId);
        args.put("productquantity", productQuantity);
        args.put("price", price);
        args.put("productname", productName);
        final Number transactionId;
        try {
            transactionId = jdbcInsert.executeAndReturnKey(args);
        } catch (AssertionError e) {
            return null;
        }
        LOGGER.info("Transaction inserted with transactionId = {}", transactionId.intValue());

        return new Transaction(transactionId.intValue(), postId, buyerUserId, productQuantity, price, productName);
    }

    @Override
    public boolean deleteTransactionByTransactionId(final Integer transactionId) {
        final Integer deletedRows = jdbcTemplate.update("DELETE FROM transactions WHERE transactionid = ?", transactionId);

        if (deletedRows == 1)
            LOGGER.info("Transaction deleted with transactionId = {}", transactionId);
        else
            LOGGER.info("Transaction not found with transactionId = {}", transactionId);
        return deletedRows == 1;
    }

    @Override
    public Optional<Transaction> findTransactionByTransactionId(final Integer transactionId) {
        return jdbcTemplate.query("SELECT * FROM transactions WHERE transactionid = ?",
                ROW_MAPPER, transactionId).stream().findFirst();
    }

    @Override
    public List<Transaction> findTransactionsByBuyerUserId(final Integer buyerUserId) {
        final List<Transaction> transactionList = jdbcTemplate.query("SELECT * FROM transactions WHERE buyeruserid = ?",
                ROW_MAPPER, buyerUserId);

        return transactionList;
    }

    @Override
    public Optional<Transaction> changeTransactionStatus(Integer transactionId, String status) {
        return Optional.empty();
    }

    @Override
    public List<Transaction> findTransactionsByUserIdAndPostId(Integer userId, Integer postId) {
        return null;
    }

    @Override
    public List<Transaction> findPurchasesByUserIdAndStatus(Integer userId, String status) {
        return null;
    }

    @Override
    public List<Transaction> findSalesByUserIdAndStatus(Integer userId, String status) {
        return null;
    }

    @Override
    public Boolean hasNoPendingTransaction(Integer postId, Integer buyerUserId) {
        return null;
    }

}
