package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.DAO.PostDAO;
import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserService userService;

    public Post createPost(final Integer productId, final Double price, final Integer userId, final String description,
                           final Integer visits) {
        return postDAO.createPost(productId, price, userId, description, visits);
    }

    @Transactional (readOnly = true)
    public Optional<Post> findPostByPostId(final Integer postId) {
        return postDAO.findPostByPostId(postId);
    }

    @Transactional (readOnly = true)
    public List<Post> findPostsByUserId(Integer userId) {
        List<Post> postsList = postDAO.findPostsByUserId(userId);

        return postsList;
    }

    @Transactional (readOnly = true)
    public List<Post> findPostsByProductId(Integer productId) {
        List<Post> postsList = postDAO.findPostsByProductId(productId);

        return postsList;
    }

    @Override
    public Optional<Post> addVisit(Integer postId) {
        return postDAO.addVisit(postId);
    }

    public Optional<Post> updatePost(final Integer postId, final Integer productId, final Double price, final String description,
                                     final Integer visits) {
        return postDAO.updatePost(postId, productId, price, description, visits);
    }

    public boolean deletePost(final Integer postId) {
        return postDAO.deletePost(postId);
    }

}
