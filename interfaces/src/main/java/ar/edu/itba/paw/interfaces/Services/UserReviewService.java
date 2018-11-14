package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.UserReview;

import java.util.List;
import java.util.Optional;

public interface UserReviewService {

    UserReview createUserReview(Integer reviewedUserId, Integer reviewerId, Integer rating, String description);

    List<UserReview> findReviewsByReviewedUserId(Integer reviewedUserId);

    Optional<UserReview> findReviewByUserReviewId(Integer userReviewId);

    List<UserReview> findReviewsByReviewerId(Integer reviewerId);

    boolean checkReviewer(Integer reviewerId, Integer reviewedUser);
}
