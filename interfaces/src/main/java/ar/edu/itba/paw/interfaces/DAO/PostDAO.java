package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostDAO {

    Post createPost(final Integer productId, final Double price, final Integer userId, final String description,
                    final Integer productQuantity);

    boolean deletePost(final Integer postId);

    Optional<Post> updatePost(final Integer postId, final Integer productId, final Double price, final String description,
                    final Integer productQuantity);

    Optional<Post> findPostByPostId(final Integer postId);

    List<Post> findPostsByUserId(final Integer userId);

    List<Post> findPostsByProductId(final Integer productId);
}
