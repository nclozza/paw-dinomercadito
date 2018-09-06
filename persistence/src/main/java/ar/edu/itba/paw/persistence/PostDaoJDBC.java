package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.PostDAO;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostDaoJDBC implements PostDAO {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

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
            resultSet.getString("description")
    );

    public Post createPost(final Integer productId, final Double price, final Integer userId, final String description) {

        final Map<String, Object> args = new HashMap<>();
        args.put("productid", productId);
        args.put("userid", userId);
        args.put("price", price);
        args.put("description", description);

        final Number postId = jdbcInsert.executeAndReturnKey(args);

        return new Post(postId.intValue(), productId, price, userId, description);
    }

    // TODO Check this method's if case for the query
    public boolean deletePost(final Integer postId) {
        final List<Post> postsList = jdbcTemplate.query("DELETE * FROM posts WHERE postid = ?", ROW_MAPPER, postId);

        return true;
    }

    public boolean updatePost(final Integer postId, final Product product, final Double price, final Integer userId,
                              final String description) {
        return false;
    }

    public Post findPostById(final Integer postId) {
        final List<Post> postsList = jdbcTemplate.query("SELECT * FROM posts WHERE postid = ?", ROW_MAPPER, postId);

        if (postsList.isEmpty()) {
            return null;
        }

        return postsList.get(0);
    }

    public List<Post> findPostsByUserId(final Integer userId) {
        final List<Post> postsList = jdbcTemplate.query("SELECT * FROM posts WHERE userid = ?", ROW_MAPPER, userId);

        if (postsList.isEmpty()) {
            return null;
        }

        return postsList;
    }
}
