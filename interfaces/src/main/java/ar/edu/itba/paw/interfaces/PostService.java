package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;

public interface PostService {

    public void getPost(Integer postId);

    public Post createPost(Product product, Double price, String username, String description);

    public boolean deletePost(String username);

    public boolean updatePost(Product product, Double price, String username, String description);
}