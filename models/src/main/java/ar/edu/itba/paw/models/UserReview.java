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
    private User reviewedUser;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User reviewer;

    public UserReview(User reviewedUser, User reviewer, Integer rating, String description) {
        this.reviewedUser = reviewedUser;
        this.reviewer = reviewer;
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

    public User getReviewedUser() {
        return reviewedUser;
    }

    public void setReviewedUser(User reviewedUser) {
        this.reviewedUser = reviewedUser;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }
}
