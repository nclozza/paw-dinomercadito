package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.PostDAO;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PostDaoJDBC implements PostDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDaoJDBC() {
    }

    // TODO Change this method's query with some other
    public Post createPost(final Product product, final Double price, final Integer userId, final String description) {
        final List<Post> postsList = (List<Post>) jdbcTemplate.query("SELECT * FROM posts WHERE postId = ?",
                new RowMapper<Post>() {
                    @Override
                    public Post mapRow(final ResultSet resultSet, int i) throws SQLException {
                        return new Post(resultSet.getInt("productId"),
                                resultSet.getDouble("price"), resultSet.getInt("userId"),
                                resultSet.getString("description"));
                    }
                });

        return postsList.get(0);
    }

    // TODO Check this method's if case for the query
    public boolean deletePost(final Integer postId) {
        final List<Post> postsList = (List<Post>) jdbcTemplate.query("DELETE * FROM posts WHERE postId = ?",
            new RowMapper<Post>() {
                @Override
                public Post mapRow(final ResultSet resultSet, int i) throws SQLException {
                    return new Post(resultSet.getInt("productId"),
                            resultSet.getDouble("price"), resultSet.getInt("userId"),
                            resultSet.getString("description"));
                }
            }, postId);

        if (postsList.isEmpty()) {
            return false;
        }

        return true;
    }

    public boolean updatePost(final Integer postId, final Product product, final Double price, final Integer userId,
                              final String description) {
        return false;
    }

    public Post findPostById(final Integer postId) {
        final List<Post> postsList = (List<Post>) jdbcTemplate.query("SELECT * FROM posts WHERE postId = ?",
            new RowMapper<Post>() {
                @Override
                public Post mapRow(final ResultSet resultSet, int i) throws SQLException {
                    return new Post(resultSet.getInt("productId"),
                            resultSet.getDouble("price"), resultSet.getInt("userId"),
                            resultSet.getString("description"));
                }
            }, postId);

        return postsList.get(0);
    }

    public List<Post> findPostsByUserId(final Integer userId) {
        final List<Post> postsList = (List<Post>) jdbcTemplate.query("SELECT * FROM posts WHERE userId = ?",
            new RowMapper<Post>() {
                @Override
                public Post mapRow(final ResultSet resultSet, int i) throws SQLException {
                    return new Post(resultSet.getInt("productId"),
                            resultSet.getDouble("price"), resultSet.getInt("userId"),
                            resultSet.getString("description"));
                }
            }, userId);

        return postsList;
    }
}
