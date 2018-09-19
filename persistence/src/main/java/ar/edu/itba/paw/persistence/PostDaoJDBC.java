package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.PostDAO;
import ar.edu.itba.paw.models.Post;
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

    public boolean deletePost(final Integer postId) {
        final Integer deletedRows = jdbcTemplate.update("DELETE FROM posts WHERE postid = ?", postId);

        return deletedRows == 1;
    }

    public Post updatePost(final Integer postId, final Integer productId, final Double price,
                           final String description) {
        jdbcTemplate.update("UPDATE posts SET productId = ?, price = ?, description = ? WHERE postid = ?",
                productId, price, description, postId);

        return findPostByPostId(postId);
    }

    public Post findPostByPostId(final Integer postId) {
        final List<Post> postsList = jdbcTemplate.query("SELECT * FROM posts WHERE postid = ?", ROW_MAPPER, postId);

        if (postsList.isEmpty()) {
            return null;
        }

        return postsList.get(0);
    }

    public List<Post> findPostByUserId(final Integer userId) {
        final List<Post> postList = jdbcTemplate.query("SELECT * FROM posts WHERE userid = ?", ROW_MAPPER, userId);

        return postList;
    }

    @Override
    public List<Post> findPostsByProductId(Integer productId) {
        final List<Post> postList = jdbcTemplate.query("SELECT * FROM posts WHERE productid = ?", ROW_MAPPER, productId);

        return postList;
    }
}
