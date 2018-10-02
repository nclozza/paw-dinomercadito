package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.Post;

import java.util.List;

public interface PostService {

    Post createPost(final Integer productId, final Double price, final Integer userId, final String description,
                    final Integer productQuantity);

    boolean deletePost(final Integer postId);

    Post updatePost(final Integer postId, final Integer productId, final Double price, final String description,
                    final Integer productQuantity);

    Post findPostByPostId(final Integer postId);

    List<Post> findPostByUserId(final Integer userId);

    List<Post> findPostsByProductId(final Integer productId);
}