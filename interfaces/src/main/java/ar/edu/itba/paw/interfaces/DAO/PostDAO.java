package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;

import java.util.List;

public interface PostDAO {

    public Post createPost(final Integer productId, final Double price, final Integer userId, final String description);

    public boolean deletePost(final Integer postId);

    public boolean updatePost(final Integer postId, final Product product, final Double price, final Integer userId,
                              final String description);

    public Post findPostById(final Integer postId);

    public List<Post> findPostsByUserId(final Integer userId);
}
