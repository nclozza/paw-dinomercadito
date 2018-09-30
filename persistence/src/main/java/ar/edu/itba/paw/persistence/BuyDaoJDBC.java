package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.BuyDAO;
import ar.edu.itba.paw.models.Buy;
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

@Repository
public class BuyDaoJDBC implements BuyDAO {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public BuyDaoJDBC(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("buys")
                .usingGeneratedKeyColumns("buyid");
    }

    private final static RowMapper<Buy> ROW_MAPPER = (resultSet, rowNum) -> new Buy(
            resultSet.getInt("buyid"),
            resultSet.getInt("postid"),
            resultSet.getInt("buyeruserid"),
            resultSet.getInt("productquantity"),
            resultSet.getDouble("price"),
            resultSet.getString("productname")
    );

    @Override
    public Buy createBuy(final Integer postId, final Integer buyerUserId, final Integer productQuantity,
                         final Double price, final String productName) {
        final Map<String, Object> args = new HashMap<>();
        args.put("postid", postId);
        args.put("buyeruserid", buyerUserId);
        args.put("productquantity", productQuantity);
        args.put("price", price);
        args.put("productname", productName);

        final Number buyId = jdbcInsert.executeAndReturnKey(args);

        return new Buy(buyId.intValue(), postId, buyerUserId, productQuantity, price, productName);
    }

    @Override
    public boolean deleteBuy(final Integer buyId) {
        final Integer deletedRows = jdbcTemplate.update("DELETE FROM buys WHERE buyid = ?", buyId);

        return deletedRows == 1;
    }

    @Override
    public Optional<Buy> findBuyByBuyId(final Integer buyId) {
        final List<Buy> buyList = jdbcTemplate.query("SELECT * FROM buys WHERE buyid = ?", ROW_MAPPER, buyId);

        if (buyList.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(buyList.get(0));
    }

    @Override
    public List<Buy> findBuysByBuyerUserId(final Integer buyerUserId) {
        final List<Buy> buyList = jdbcTemplate.query("SELECT * FROM buys WHERE buyeruserid = ?", ROW_MAPPER, buyerUserId);

        return buyList;
    }
}
