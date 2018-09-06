package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.DAO.PostDAO;
import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDaoJDBC;

    public Post findPostById(Integer postId) {
        return postDaoJDBC.findPostById(postId);
    }

    public List<Post> findPostsByUserId(Integer userId) {
        List<Post> postsList = postDaoJDBC.findPostsByUserId(userId);

        if (postsList.isEmpty()) {
            return null;
        }

        return postsList;
    }

    public Post createPost(final Integer productId, final Double price, final Integer userId, final String description) {
        return postDaoJDBC.createPost(productId, price, userId, description);
    }

    public boolean updatePost(final Integer postId, Product product, Double price, final Integer userId, String description) {
        return false;
    }

    public boolean deletePost(final Integer postId) {
        return postDaoJDBC.deletePost(postId);
    }
}
