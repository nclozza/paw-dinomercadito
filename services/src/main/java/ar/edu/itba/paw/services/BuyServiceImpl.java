package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.BuyDAO;
import ar.edu.itba.paw.interfaces.Services.BuyService;
import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.Buy;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BuyServiceImpl implements BuyService {

    @Autowired
    private BuyDAO buyDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ProductService productService;

    @Override
    public Buy createBuy(final Integer postId, final Integer buyerUserId, final Integer productQuantity,
                         final Double price, final String productName) {
        return buyDAO.createBuy(postId, buyerUserId, productQuantity, price, productName);
    }

    @Override
    public boolean deleteBuy(final Integer buyId) {
        return buyDAO.deleteBuy(buyId);
    }

    @Override
    public Optional<Buy> findBuyByBuyId(final Integer buyId) {
        return buyDAO.findBuyByBuyId(buyId);
    }

    @Override
    public List<Buy> findBuysByBuyerUserId(final Integer buyerUserId) {
        return buyDAO.findBuysByBuyerUserId(buyerUserId);
    }

    @Override
    public Integer buyTransaction(Integer buyerUserId, Integer postId, Integer productQuantity) {
        User buyerUser = userService.findUserByUserId(buyerUserId);
        Post post = postService.findPostByPostId(postId);
        Product product = productService.findProductByProductId(post.getProductId());


        if (buyerUser == null || post == null || product == null) {
            return -1;
        }

        if (productQuantity > post.getProductQuantity()) {
            return 0;
        }

        if (buyerUser.getFunds() < post.getPrice() * productQuantity) {
            return 1;
        }

        userService.updateUser(buyerUser.getUserId(), buyerUser.getPassword(), buyerUser.getEmail(), buyerUser.getPhone(),
                buyerUser.getBirthdate(), buyerUser.getFunds() - post.getPrice() * productQuantity);

        postService.updatePost(post.getPostId(), post.getProductId(), post.getPrice(), post.getDescription(),
                post.getProductQuantity() - productQuantity);

        createBuy(postId, buyerUserId, productQuantity, post.getPrice(), product.getProductName());

        return 2;
    }
}
