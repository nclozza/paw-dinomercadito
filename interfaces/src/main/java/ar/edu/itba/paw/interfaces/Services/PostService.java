package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;

import java.util.List;

public interface PostService {

    public Post createPost(final Integer productId, final Double price, final Integer userId, final String description);

    public boolean deletePost(final Integer postId);

    public Post updatePost(final Integer postId, final Integer productId, final Double price,
                           final String description);

    public Post findPostByPostId(final Integer postId);

    public List<Post> findPostByUserId(final Integer userId);
}