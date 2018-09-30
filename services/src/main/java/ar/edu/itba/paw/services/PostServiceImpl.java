package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.DAO.PostDAO;
import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserService userService;

    public Post findPostByPostId(Integer postId) {
        return postDAO.findPostByPostId(postId);
    }

    public List<Post> findPostByUserId(Integer userId) {
        List<Post> postsList = postDAO.findPostByUserId(userId);

        if (postsList.isEmpty()) {
            return null;
        }

        return postsList;
    }

    @Override
    public List<Post> findPostsByProductId(Integer productId) {
        List<Post> postsList = postDAO.findPostsByProductId(productId);

        if (postsList.isEmpty()) {
            return null;
        }

        return postsList;
    }

    public Post createPost(final Integer productId, final Double price, final Integer userId, final String description,
                           final Integer productQuantity) {
        return postDAO.createPost(productId, price, userId, description, productQuantity);
    }

    public Post updatePost(final Integer postId, final Integer productId, final Double price, final String description,
                           final Integer productQuantity) {
        return postDAO.updatePost(postId, productId, price, description, productQuantity);
    }

    public boolean deletePost(final Integer postId) {
        return postDAO.deletePost(postId);
    }
}
