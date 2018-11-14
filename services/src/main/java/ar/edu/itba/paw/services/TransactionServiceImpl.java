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

import java.util.LinkedList;
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

    @Override
    public Transaction createTransaction(final Integer postId, final Integer buyerUserId, final Integer productQuantity,
                                         final Double price, final String productName) {
        return transactionDAO.createTransaction(postId, buyerUserId, productQuantity, price, productName);
    }

    public boolean deleteTransactionByTransactionId(final Integer transactionId) {
        return transactionDAO.deleteTransactionByTransactionId(transactionId);
    }

    @Transactional (readOnly = true)
    @Override
    public Optional<Transaction> findTransactionByTransactionId(final Integer transactionId) {
        return transactionDAO.findTransactionByTransactionId(transactionId);
    }

    @Transactional (readOnly = true)
    @Override
    public List<Transaction> findTransactionsByBuyerUserId(final Integer buyerUserId) {
        return transactionDAO.findTransactionsByBuyerUserId(buyerUserId);
    }

    @Override
    public Integer makeTransaction(Integer buyerUserId, Integer postId, Integer productQuantity) {
        Optional<User> buyerUser = userService.findUserByUserId(buyerUserId);
        Optional<Post> post = postService.findPostByPostId(postId);

        if (!post.isPresent()) {
            LOGGER.error("Wrong information to make the transaction");
            return Transaction.WRONG_PARAMETERS;
        }

        Optional<Product> product = productService.findProductByProductId(post.get().getProductPosted().getProductId());

        if (!buyerUser.isPresent() || !product.isPresent()) {
            LOGGER.error("Wrong information to make the transaction");
            return Transaction.INCOMPLETE;
        }

        if (buyerUserId == post.get().getUserSeller().getUserId()){
            LOGGER.error("BuyerUser and SellerUser are the same");
            return Transaction.SAME_USER;
        }

        if (transactionDAO.findPendingTransaction(postId, buyerUserId)){
            LOGGER.error("Buy in pending for userid = {}", buyerUserId);
            return Transaction.PENDING_BUY;
        }

        Transaction transaction = createTransaction(postId, buyerUserId, productQuantity, post.get().getPrice(),
                product.get().getProductName());

        return transaction.getTransactionId();
    }

    @Override
    public Optional<Transaction> changeTransactionStatus(Integer transactionId, String status) {
        return transactionDAO.changeTransactionStatus(transactionId, status);
    }

    @Override
    public Boolean findTransactionsByUserIdAndPostId(Integer userId, Integer postId) {
        List<Transaction> transactionList = transactionDAO.findTransactionsByUserIdAndPostId(userId, postId);

        if(!transactionList.isEmpty())
            return true;
        else
            return false;
    }

    @Override
    public List<Transaction> findPurchasesByUserIdAndStatus(Integer userId, String status) {
        return transactionDAO.findPurchasesByUserIdAndStatus(userId, status);
    }

    @Override
    public List<Transaction> findSalesByUserIdAndStatus(Integer userId, String status) {
        return  transactionDAO.findSalesByUserIdAndStatus(userId, status);
    }

    @Override
    public Boolean isValidTransaction(Transaction transaction){

        if(transaction.getProductQuantity() > transaction.getBoughtPost().getProductQuantity())
            return false;
        else
            return true;
    }

    @Override
    public void confirmTransaction(Transaction transaction){
        postService.updatePostProductQuantity(transaction.getBoughtPost().getPostId(),
                transaction.getBoughtPost().getProductQuantity() - transaction.getProductQuantity());

        transactionDAO.changeTransactionStatus(transaction.getTransactionId(), Transaction.CONFIRMED);
    }
}
