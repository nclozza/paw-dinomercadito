package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.PostDAO;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PostDaoJDBC  implements PostDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDaoJDBC() {
    }

    public Post findPostById(Integer postId) {
        return null;
    }

    public Post createPost(Product product, final Double price, final Integer userId, final String description) {
        return null;
    }

    public boolean deletePost(Integer postId) {
        return false;
    }

    public boolean updatePost(final Integer postId, final Product product, final Double price, final Integer userId,
                              final String description) {
        return false;
    }
}
