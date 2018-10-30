package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    public static final Integer MAX_TOP_VISITED = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_postId_seq")
    @SequenceGenerator(sequenceName = "posts_postId_seq", name = "posts_postId_seq", allocationSize = 1)
    @Column(name = "postId")
    private Integer postId;

    @Column(nullable = false)
    private Integer productId;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer userId;

    @Column(length = 128)
    private String description;

    @Column(nullable = false)
    private Integer productQuantity;

    @Column(nullable = false)
    private Integer visits;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Product product;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "post")
    private List<Transaction> transactionsList;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "postInView")
    private List<View> viewsList;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "postToAsk")
    private List<Question> questionList;

    public Post(final Integer postId, final Integer productId, final Double price, final Integer userId,
                final String description) {
        this.postId = postId;
        this.productId = productId;
        this.price = price;
        this.userId = userId;
        this.description = description;
        this.visits = 0;
    }

    public Post(final Integer postId, final Integer productId, final Double price, final Integer userId,
                final String description, final Integer visits) {
        this.postId = postId;
        this.productId = productId;
        this.price = price;
        this.userId = userId;
        this.description = description;
        this.visits = visits;
    }

    public Post(final Product product, final Double price, final User user,
                final String description, final Integer productQuantity,final Integer visits) {
        this.product = product;
        this.productId = product.getProductId();
        this.price = price;
        this.user = user;
        this.userId = user.getUserId();
        this.description = description;
        this.productQuantity = productQuantity;
        this.visits = visits;
    }

    public Post(){
        //Just for Hibernate
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

    public void setProductId(final Integer productId) {
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

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(final Integer visits) {
        this.visits = visits;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transaction> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public List<View> getViewsList() {
        return viewsList;
    }

    public void setViewsList(List<View> viewsList) {
        this.viewsList = viewsList;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
