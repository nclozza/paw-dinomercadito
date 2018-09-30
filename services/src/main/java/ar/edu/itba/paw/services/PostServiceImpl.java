package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.DAO.PostDAO;
import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserService userService;

    public Post createPost(final Integer productId, final Double price, final Integer userId, final String description,
                           final Integer productQuantity) {
        return postDAO.createPost(productId, price, userId, description, productQuantity);
    }

    @Transactional (readOnly = true)
    public Post findPostByPostId(Integer postId) throws IllegalStateException {
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

    public Post updatePost(final Integer postId, final Integer productId, final Double price, final String description,
                           final Integer productQuantity) {
        Post post = postDAO.findPostByPostId(postId);
        return postDAO.updatePost(postId, productId, price, post.getUserId(), description, productQuantity);
    }

    public boolean deletePost(final Integer postId) {
        return postDAO.deletePost(postId);
    }

    public void buyProduct(final Integer buyerId, final Integer postId) {
        Post post = postDAO.findPostByPostId(postId);
        Integer productQuantity = post.getProductQuantity();
        Double price = post.getPrice();
        User seller = userService.findUserByUserId(post.getUserId());
        User buyer = userService.findUserByUserId(buyerId);
        Double buyerFunds = buyer.getFunds();

        userService.updateUser(seller.getUserId(), seller.getPassword(), seller.getEmail(), seller.getPhone(),
                seller.getBirthdate(), seller.getFunds() + price);
        userService.updateUser(buyer.getUserId(), buyer.getPassword(), buyer.getEmail(), buyer.getPhone(),
                buyer.getBirthdate(), buyerFunds - price);
        postDAO.updatePost(postId, post.getProductId(), price, seller.getUserId(), post.getDescription(),
                productQuantity - 1);
    }
}
