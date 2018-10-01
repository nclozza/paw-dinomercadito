package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.DAO.TransactionDAO;
import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.interfaces.Services.TransactionService;
import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.Transaction;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ProductService productService;

    @Override
    public Transaction createTransaction(final Integer postId, final Integer buyerUserId, final Integer productQuantity,
                                 final Double price, final String productName) {
        return transactionDAO.createTransaction(postId, buyerUserId, productQuantity, price, productName);
    }

    @Override
    public boolean deleteTransaction(final Integer transactionId) {
        return transactionDAO.deleteTransaction(transactionId);
    }

    @Override
    public Optional<Transaction> findTransactionByTransactionId(final Integer transactionId) {
        return transactionDAO.findTransactionByTransactionId(transactionId);
    }

    @Override
    public List<Transaction> findTransactionsByBuyerUserId(final Integer buyerUserId) {
        return transactionDAO.findTransactionsByBuyerUserId(buyerUserId);
    }

    @Override
    public Integer makeTransaction(Integer buyerUserId, Integer postId, Integer productQuantity) {
        User buyerUser = userService.findUserByUserId(buyerUserId);
        Post post = postService.findPostByPostId(postId);
        Product product = productService.findProductByProductId(post.getProductId());


        if (buyerUser == null || post == null || product == null) {
            return Transaction.INCOMPLETE;
        }

        if (productQuantity > post.getProductQuantity()) {
            return Transaction.OUT_OF_STOCK_FAIL;
        }

        if (buyerUser.getFunds() < post.getPrice() * productQuantity) {
            return Transaction.INSUFFICIENT_FUNDS_FAIL;
        }

        userService.updateUser(buyerUser.getUserId(), buyerUser.getPassword(), buyerUser.getEmail(), buyerUser.getPhone(),
                buyerUser.getBirthdate(), buyerUser.getFunds() - post.getPrice() * productQuantity);

        postService.updatePost(post.getPostId(), post.getProductId(), post.getPrice(), post.getDescription(),
                post.getProductQuantity() - productQuantity);

        Transaction transaction = createTransaction(postId, buyerUserId, productQuantity, post.getPrice(), product.getProductName());

        return transaction.getTransactionId();
    }
}
