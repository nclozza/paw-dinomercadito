package ar.edu.itba.paw.models;

public class Buy {

    private Integer buyId;
    private Integer postId;
    private Integer buyerUserId;
    private Integer productQuantity;
    private Double price;

    public Buy(final Integer buyId, final Integer postId, final Integer buyerUserId, final Integer productQuantity,
               final Double price) {
        this.buyId = buyId;
        this.postId = postId;
        this.buyerUserId = buyerUserId;
        this.productQuantity = productQuantity;
        this.price = price;
    }

    public Integer getBuyId() {
        return buyId;
    }

    public void setBuyId(Integer buyId) {
        this.buyId = buyId;
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
}
