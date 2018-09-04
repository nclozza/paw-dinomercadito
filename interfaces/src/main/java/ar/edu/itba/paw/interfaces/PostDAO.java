package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;

public interface PostDAO {

    public Post findPostById(Integer postId);

    public Post createPost(final Product product, final Double price, final Integer userId, final String description);

    public boolean deletePost(final Integer postId);

    public boolean updatePost(final Integer postId, final Product product, final Double price, final Integer userId,
                              final String description);
}
