package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.ProductDAO;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoJDBC implements ProductDAO {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    /*
    String productName, String brand, String ram, String storage, String operativeSystem,
                                 String processor, String bodySize, String screenSize, String screenRatio,
                                 String rearCamera, String frontCamera
    */
    private final static RowMapper<Product> ROW_MAPPER = (resultSet, rowNum) -> new Product(
            resultSet.getInt("productId"),
            resultSet.getString("productName"),
            resultSet.getString("brand"),
            resultSet.getString("ram"),
            resultSet.getString("storage"),
            resultSet.getString("operativeSystem"),
            resultSet.getString("processor"),
            resultSet.getString("bodySize"),
            resultSet.getString("screenSize"),
            resultSet.getString("screenRatio"),
            resultSet.getString("rearCamera"),
            resultSet.getString("frontCamera")
    );

    @Autowired
    public ProductDaoJDBC(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("posts")
                .usingGeneratedKeyColumns("postid");
    }

    @Override
    public Product createProduct(String productName, String brand, String ram, String storage, String operativeSystem,
                                 String processor, String bodySize, String screenSize, String screenRatio,
                                 String rearCamera, String frontCamera) {
        final Map<String, Object> args = new HashMap<>();

        args.put("productname", productName);
        args.put("brand", brand);
        args.put("storage", storage);
        args.put("operativeSystem", operativeSystem);
        args.put("processor", processor);
        args.put("bodySize", bodySize);
        args.put("screenSize", screenSize);
        args.put("screenRatio", screenRatio);
        args.put("rearCamera", rearCamera);
        args.put("frontCamera", frontCamera);

        final Number productId = jdbcInsert.executeAndReturnKey(args);

        return new Product(productId.intValue(), productName, brand, ram, storage, operativeSystem, processor, bodySize,
                screenSize, screenRatio, rearCamera, frontCamera);
    }

    @Override
    public boolean deleteProduct(Integer productId) {
        return false;
    }

    @Override
    public Product findProductByProductId(Integer productId) {
        final List<Product> productList = jdbcTemplate.query("SELECT * FROM products WHERE productid = ?", ROW_MAPPER,
                productId);

        return productList.get(0);
    }

    @Override
    public Product updateProduct(String productName, String brand, String ram, String storage, String operativeSystem, String processor, Rectangle bodySize, Rectangle screenSize, String screenRatio, String rearCamera, String frontCamera) {
        return null;
    }
}
