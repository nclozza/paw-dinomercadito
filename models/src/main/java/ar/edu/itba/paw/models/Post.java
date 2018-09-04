package ar.edu.itba.paw.models;

public class Post {

    private Integer id;
    private Product product;
    private Double price;
    private String username;
    private String description;

    public Post(Integer id, Product product, Double price, String username, String description) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.username = username;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
