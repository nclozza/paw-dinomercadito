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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ProductService productService;

    public Transaction createTransaction(final Integer postId, final Integer buyerUserId, final Integer productQuantity,
                                         final Double price, final String productName) {
        return transactionDAO.createTransaction(postId, buyerUserId, productQuantity, price, productName);
    }

    public boolean deleteTransaction(final Integer transactionId) {
        return transactionDAO.deleteTransaction(transactionId);
    }

    @Transactional (readOnly = true)
    public Optional<Transaction> findTransactionByTransactionId(final Integer transactionId) {
        return transactionDAO.findTransactionByTransactionId(transactionId);
    }

    @Transactional (readOnly = true)
    public List<Transaction> findTransactionsByBuyerUserId(final Integer buyerUserId) {
        return transactionDAO.findTransactionsByBuyerUserId(buyerUserId);
    }

    @Override
    public Integer makeTransaction(Integer buyerUserId, Integer postId, Integer productQuantity) {
        User buyerUser = userService.findUserByUserId(buyerUserId);
        Post post = postService.findPostByPostId(postId);
        Product product = productService.findProductByProductId(post.getProductId());


        if (buyerUser == null || post == null || product == null) {
            LOGGER.error("Incomplete information to make the transaction");
            return Transaction.INCOMPLETE;
        }

        if (post.getProductQuantity() < productQuantity) {
            LOGGER.error("The product amount selected for this post is bigger than the available stock");
            return Transaction.OUT_OF_STOCK_FAIL;
        }

        if (buyerUser.getFunds() < (post.getPrice() * productQuantity)) {
            LOGGER.error("The user has not enough funds to make the transaction");
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
