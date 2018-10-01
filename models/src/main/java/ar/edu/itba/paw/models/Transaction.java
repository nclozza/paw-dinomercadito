package ar.edu.itba.paw.models;

public class Transaction {

    public static final Integer INCOMPLETE = -3;
    public static final Integer OUT_OF_STOCK_FAIL = -2;
    public static final Integer INSUFFICIENT_FUNDS_FAIL = -1;

    private Integer transactionId;
    private Integer postId;
    private Integer buyerUserId;
    private Integer productQuantity;
    private Double price;
    private String productName;

    public Transaction(final Integer transactionId, final Integer postId, final Integer buyerUserId, final Integer productQuantity,
               final Double price, final String productName) {
        this.transactionId = transactionId;
        this.postId = postId;
        this.buyerUserId = buyerUserId;
        this.productQuantity = productQuantity;
        this.price = price;
        this.productName = productName;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(Integer buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
