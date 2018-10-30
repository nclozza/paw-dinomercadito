package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "userReviews")
public class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userReviews_userReviewId_seq")
    @SequenceGenerator(sequenceName = "userReviews_userReviewId_seq", name = "userReviews_userReviewId_seq", allocationSize = 1)
    @Column(name = "userReviewId")
    private Integer userReviewId;

    @Column(nullable = false)
    private Integer rating;

    @Column(length = 128)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User userReviewed;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User userWhoReview;

    public UserReview(User userReviewed, User userWhoReview, Integer rating, String description) {
        this.userReviewed = userReviewed;
        this.userWhoReview = userWhoReview;
        this.rating = rating;
        this.description = description;
    }

    public UserReview() {
        //Just for hibernate
    }

    public Integer getUserReviewId() {
        return userReviewId;
    }

    public void setUserReviewId(Integer userReviewId) {
        this.userReviewId = userReviewId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserReviewed() {
        return userReviewed;
    }

    public void setUserReviewed(User userReviewed) {
        this.userReviewed = userReviewed;
    }

    public User getUserWhoReview() {
        return userWhoReview;
    }

    public void setUserWhoReview(User userWhoReview) {
        this.userWhoReview = userWhoReview;
    }
}
