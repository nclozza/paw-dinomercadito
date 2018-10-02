package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.TransactionDAO;
import ar.edu.itba.paw.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
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

        final Number transactionId = jdbcInsert.executeAndReturnKey(args);

        LOGGER.info("Transaction inserted with transactionId = " + transactionId.intValue());

        return new Transaction(transactionId.intValue(), postId, buyerUserId, productQuantity, price, productName);
    }

    @Override
    public boolean deleteTransaction(final Integer transactionId) {
        final Integer deletedRows = jdbcTemplate.update("DELETE FROM transactions WHERE buyid = ?", transactionId);

        LOGGER.info("Transaction deleted with transactionId = " + transactionId);

        return deletedRows == 1;
    }

    @Override
    public Optional<Transaction> findTransactionByTransactionId(final Integer transactionId) {
        final List<Transaction> transactionList = jdbcTemplate.query("SELECT * FROM transactions WHERE transactionid = ?",
                ROW_MAPPER, transactionId);

        if (transactionList.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(transactionList.get(0));
    }

    @Override
    public List<Transaction> findTransactionsByBuyerUserId(final Integer buyerUserId) {
        final List<Transaction> transactionList = jdbcTemplate.query("SELECT * FROM transactions WHERE buyeruserid = ?",
                ROW_MAPPER, buyerUserId);

        return transactionList;
    }
}
