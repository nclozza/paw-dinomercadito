package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.PostDAO;
import ar.edu.itba.paw.models.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public class PostDaoJDBC implements PostDAO {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private static final Logger LOGGER = LoggerFactory.getLogger(PostDaoJDBC.class);

    @Autowired
    public PostDaoJDBC(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("posts")
                .usingGeneratedKeyColumns("postid");
    }

    private final static RowMapper<Post> ROW_MAPPER = (resultSet, rowNum) -> new Post(
            resultSet.getInt("postid"),
            resultSet.getInt("productid"),
            resultSet.getDouble("price"),
            resultSet.getInt("userid"),
            resultSet.getString("description"),
            resultSet.getInt("productquantity")
    );

    public Post createPost(final Integer productId, final Double price, final Integer userId, final String description,
                           final Integer productQuantity, final Integer visits) {
        final Map<String, Object> args = new HashMap<>();
        args.put("productid", productId);
        args.put("userid", userId);
        args.put("price", price);
        args.put("description", description);
        args.put("productquantity", productQuantity);

        final Number postId = jdbcInsert.executeAndReturnKey(args);

        LOGGER.info("Post inserted with postId = {}", postId.intValue());

        return new Post(postId.intValue(), productId, price, userId, description, productQuantity);
    }

    public boolean deletePost(final Integer postId) {
        final Integer deletedRows = jdbcTemplate.update("DELETE FROM posts WHERE postid = ?", postId);

        if (deletedRows == 1)
            LOGGER.info("Post deleted with postId = {}", postId);
        else
            LOGGER.info("Post not found with postId = {}", postId);

        return deletedRows == 1;
    }

    public Optional<Post> updatePost(final Integer postId, final Integer productId, final Double price, final String description,
                           final Integer productQuantity, final Integer Visits) {
        jdbcTemplate.update("UPDATE posts SET productId = ?, price = ?, description = ?, productQuantity = ? " +
                        "WHERE postid = ?", productId, price, description, productQuantity, postId);

        LOGGER.info("Post updated with postId = {}", postId);

        return findPostByPostId(postId);
    }

    public Optional<Post> findPostByPostId(final Integer postId) {

        return jdbcTemplate.query("SELECT * FROM posts WHERE postid = ?", ROW_MAPPER, postId)
                .stream().findFirst();
    }

    public List<Post> findPostsByUserId(final Integer userId) {
        final List<Post> postsList = jdbcTemplate.query("SELECT * FROM posts WHERE userid = ?", ROW_MAPPER, userId);

        return postsList;
    }

    public List<Post> findPostsByProductId(Integer productId) {
        final List<Post> postsList = jdbcTemplate.query("SELECT * FROM posts WHERE productid = ?", ROW_MAPPER, productId);

        return postsList;
    }

    @Override
    public Optional<Post> addVisit(Integer postId) {
        return Optional.empty();
    }

    @Override
    public List<Post> findMostVisitedPosts() {
        return null;
    }

    @Override
    public Optional<Post> updatePostProductQuantity(Integer postId, Integer productQuantity) {
        return Optional.empty();
    }
}
