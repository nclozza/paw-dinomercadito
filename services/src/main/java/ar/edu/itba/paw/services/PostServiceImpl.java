package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImpl implements PostService {

    @Autowired
    private PostDaoJDBC postDaoJDBC;

    @Autowired
    public PostServiceImpl() {

    }

    public Post getPost(Integer postId) {
        return postDaoJDBC.get(postId);
    }

    public Post createPost(Product product, Double price, String username, String description) {
        return postDaoJDBC.create(product, price, username, description);
    }

    public boolean deletePost(final Integer postId) {
        return postDaoJDBC.delete(postId);
    }

    public boolean updatePost(Product product, Double price, String username, String description) {
        return postDaoJDBC.update(product, price, username, description);
    }
}
