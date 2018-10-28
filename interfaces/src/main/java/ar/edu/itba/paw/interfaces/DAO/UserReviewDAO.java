package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.UserReview;

import java.util.List;
import java.util.Optional;

public interface UserReviewDAO {

    UserReview createUserReview(Integer userReviewedId, Integer userWhoReviewId, Integer rating, String description);

    List<UserReview> findReviewsByUserReviewedId(Integer userReviewedId);

    Optional<UserReview> findReviewByUserReviewId(Integer userReviewId);

    List<UserReview> findReviewsByUserWhoReviewId(Integer userWhoReviewId);
}
