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
    private Double price;

    @Column(length = 128)
    private String description;

    @Column(nullable = false)
    private Integer productQuantity;

    @Column(nullable = false)
    private Integer visits;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Product productPosted;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User userSeller;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "postBought")
    private List<Transaction> transactionsList;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "postVisited")
    private List<View> viewsList;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "postAsked")
    private List<Question> questionList;

    public Post(final Integer postId, final Integer productId, final Double price, final Integer userId,
                final String description) {
        this.postId = postId;
        this.productPosted.setProductId(productId);
        this.price = price;
        this.userSeller.setUserId(userId);
        this.description = description;
        this.visits = 0;
    }

    public Post(final Integer postId, final Integer productId, final Double price, final Integer userId,
                final String description, final Integer visits) {
        this.postId = postId;
        this.productPosted.setProductId(productId);
        this.price = price;
        this.userSeller.setUserId(userId);
        this.description = description;
        this.visits = visits;
    }

    public Post(final Product product, final Double price, final User user,
                final String description, final Integer productQuantity,final Integer visits) {
        this.productPosted = product;
        this.price = price;
        this.userSeller = user;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
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

    public User getUserSeller() {
        return userSeller;
    }

    public void setUserSeller(User userSeller) {
        this.userSeller = userSeller;
    }

    public Product getProductPosted() {
        return productPosted;
    }

    public void setProductPosted(Product productPosted) {
        this.productPosted = productPosted;
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
