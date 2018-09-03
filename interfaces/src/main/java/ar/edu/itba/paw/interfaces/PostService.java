package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;

public interface PostService {

    public void getPost(final Integer postId);

    public Post createPost(final Product product, final Double price, final String username, final String description);

    public boolean deletePost(final Integer postId);

    public boolean updatePost(final Product product, final Double price, final String username, final String description);
}