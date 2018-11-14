package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.UserReviewDAO;
import ar.edu.itba.paw.interfaces.Services.UserReviewService;
import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.UserReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserReviewServiceImpl implements UserReviewService {

    @Autowired
    private UserReviewDAO userReviewDAO;

    @Autowired
    private UserService userService;

    @Override
    public UserReview createUserReview(Integer reviewedUserId, Integer reviewerId, Integer rating, String description) {
        return userReviewDAO.createUserReview(reviewedUserId, reviewerId, rating, description);
    }

    @Override
    public List<UserReview> findReviewsByReviewedUserId(Integer reviewedUserId) {
        return userReviewDAO.findReviewsByReviewedUserId(reviewedUserId);
    }

    @Override
    public List<UserReview> findReviewsByReviewerId(Integer reviewerId) {
        return userReviewDAO.findReviewsByReviewerId(reviewerId);
    }

    @Override
    public Optional<UserReview> findReviewByUserReviewId(Integer userReviewId) {
        return userReviewDAO.findReviewByUserReviewId(userReviewId);
    }

    @Override
    public boolean checkReviewer(Integer reviewerId, Integer reviewedUser){
        List<UserReview> userReviewList = userReviewDAO.findReviewsByReviewerId(reviewerId);

        for(UserReview ur : userReviewList){
            if(ur.getReviewedUser().getUserId() == reviewedUser)
                return false;
        }

        return true;
    }

}
