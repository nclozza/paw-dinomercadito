package ar.edu.itba.paw;

import ar.edu.itba.paw.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PostDaoJDBC {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDaoJDBC() {
    }

    public Post get(final Integer postId) {
        return null;
    }
}
