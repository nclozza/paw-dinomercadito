package ar.edu.itba.paw.models;

public class Post {

    private Integer postId;
    private Integer productId;
    private Double price;
    private Integer userId;
    private String description;
    private Integer productQuantity;

    public Post(final Integer postId, final Integer productId, final Double price, final Integer userId,
                final String description) {
        this.postId = postId;
        this.productId = productId;
        this.price = price;
        this.userId = userId;
        this.description = description;
        this.productQuantity = 1;
    }

    public Post(final Integer postId, final Integer productId, final Double price, final Integer userId,
                final String description, final Integer productQuantity) {
        this.postId = postId;
        this.productId = productId;
        this.price = price;
        this.userId = userId;
        this.description = description;
        this.productQuantity = productQuantity;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(final Integer postId) {
        this.postId = postId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(final Integer ProductId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(final Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}
