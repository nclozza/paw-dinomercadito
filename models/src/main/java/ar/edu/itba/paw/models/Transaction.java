package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    public static final Integer SAME_USER = -3;
    public static final Integer INCOMPLETE = -2;
    public static final Integer WRONG_PARAMETERS = -1;
    public static final String PENDING = "Pending";
    public static final String CONFIRMED = "Confirmed";
    public static final String DECLINED = "Declined";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_transactionId_seq")
    @SequenceGenerator(sequenceName = "transactions_transactionId_seq", name = "transactions_transactionId_seq", allocationSize = 1)
    @Column(name = "transactionId")
    private Integer transactionId;

    @Column(nullable = false)
    private Integer productQuantity;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 32)
    private String productName;

    @Column(nullable = false, length = 16)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User buyerUser;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Post postBuyed;

    public Transaction(final Integer transactionId, final Integer postId, final Integer buyerUserId,
                       final Integer productQuantity, final Double price, final String productName) {
        this.transactionId = transactionId;
        this.postBuyed.setPostId(postId);
        this.buyerUser.setUserId(buyerUserId);
        this.productQuantity = productQuantity;
        this.price = price;
        this.productName = productName;
    }

    public Transaction(final Post post, final User buyerUser,
                       final Integer productQuantity, final Double price, final String productName) {
        this.postBuyed = post;
        this.buyerUser = buyerUser;
        this.productQuantity = productQuantity;
        this.price = price;
        this.productName = productName;
        this.status = Transaction.PENDING;
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

    public Post getPostBuyed() {
        return postBuyed;
    }

    public void setPostBuyed(Post postBuyed) {
        this.postBuyed = postBuyed;
    }

    public User getBuyerUser() {
        return buyerUser;
    }

    public void setBuyerUser(User buyerUser) {
        this.buyerUser = buyerUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
