package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    public static final Integer INCOMPLETE = -4;
    public static final Integer WRONG_PARAMETERS = -3;
    public static final Integer OUT_OF_STOCK_FAIL = -2;
    public static final Integer INSUFFICIENT_FUNDS_FAIL = -1;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_transactionId_seq")
    @SequenceGenerator(sequenceName = "transactions_transactionId_seq", name = "transactions_transactionId_seq", allocationSize = 1)
    @Column(name = "transactionId")
    private Integer transactionId;

    @Column(nullable = false)
    private Integer postId;

    @Column(nullable = false)
    private Integer buyerUserId;

    @Column(nullable = false)
    private Integer productQuantity;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 32)
    private String productName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User buyerUser;

    public Transaction(final Integer transactionId, final Integer postId, final Integer buyerUserId,
                       final Integer productQuantity, final Double price, final String productName) {
        this.transactionId = transactionId;
        this.postId = postId;
        this.buyerUserId = buyerUserId;
        this.productQuantity = productQuantity;
        this.price = price;
        this.productName = productName;
    }

    public Transaction(final Post post, final User buyerUser,
                       final Integer productQuantity, final Double price, final String productName) {
        this.post = post;
        this.postId = post.getPostId();
        this.buyerUser = buyerUser;
        this.buyerUserId = buyerUser.getUserId();
        this.productQuantity = productQuantity;
        this.price = price;
        this.productName = productName;
    }

    public Transaction(){
        //Just for Hibernate
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(final Integer postId) {
        this.postId = postId;
    }

    public Integer getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(final Integer buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(final Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getBuyerUser() {
        return buyerUser;
    }

    public void setBuyerUser(User buyerUser) {
        this.buyerUser = buyerUser;
    }

}
