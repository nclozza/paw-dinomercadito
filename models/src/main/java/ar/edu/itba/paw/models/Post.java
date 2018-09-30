package ar.edu.itba.paw.models;

public class Post {

    private Integer postId;
    private Integer productId;
    private Double price;
    private Integer userId;
    private String description;
    private Integer productQuantity = 1;

    public Post(Integer postId, Integer productId, Double price, Integer userId, String description) {
        this.postId = postId;
        this.productId = productId;
        this.price = price;
        this.userId = userId;
        this.description = description;
    }

    public Post(Integer postId, Integer productId, Double price, Integer userId, String description, Integer productQuantity) {
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

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer ProductId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}
