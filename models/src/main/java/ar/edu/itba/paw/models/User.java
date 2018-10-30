package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_userId_seq")
    @SequenceGenerator(sequenceName = "users_userId_seq", name = "users_userId_seq", allocationSize = 1)
    @Column(name = "userId")
    private Integer userId;

    @Column(length = 32, nullable = false, unique = true)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 32)
    private String email;

    @Column(length = 16)
    private String phone;

    @Column(length = 10)
    private String birthdate;

    @Column
    private Double rating;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<Post> postList;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<Address> addressesList;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "buyerUser")
    private List<Transaction> transactionsList;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<View> viewsList;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "userReviewed")
    private List<UserReview> userReviewedList;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "userWhoReview")
    private List<UserReview> userWhoReviewList;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "userWhoAsk")
    private List<Question> questionList;

    public User(final String username, final String password, final String email,
                final String phone, final String birthdate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    public User(final Integer userId, final String username, final String password, final String email,
                final String phone, final String birthdate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    public User(final Integer userId, final String username, final String password, final String email,
                final String phone, final String birthdate, final Double funds) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    public User(){
        //Just for Hibernate
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(final String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public List<Address> getAddressesList() {
        return addressesList;
    }

    public void setAddressesList(List<Address> addressesList) {
        this.addressesList = addressesList;
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

    public List<UserReview> getUserReviewedList() {
        return userReviewedList;
    }

    public void setUserReviewedList(List<UserReview> userReviewedList) {
        this.userReviewedList = userReviewedList;
    }

    public List<UserReview> getUserWhoReviewList() {
        return userWhoReviewList;
    }

    public void setUserWhoReviewList(List<UserReview> userWhoReviewList) {
        this.userWhoReviewList = userWhoReviewList;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
