package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.UserReviewDAO;
import ar.edu.itba.paw.interfaces.Services.UserReviewService;
import ar.edu.itba.paw.models.UserReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserReviewServiceImpl implements UserReviewService {

    @Autowired
    private UserReviewDAO userReviewDAO;


    @Override
    public UserReview createUserReview(Integer userReviewedId, Integer userWhoReviewId, Integer rating, String description) {
        return userReviewDAO.createUserReview(userReviewedId, userWhoReviewId, rating, description);
    }

    @Override
    public List<UserReview> findReviewsByUserReviewedId(Integer userReviewedId) {
        return userReviewDAO.findReviewsByUserReviewedId(userReviewedId);
    }

    @Override
    public Optional<UserReview> findReviewByUserReviewId(Integer userReviewId) {
        return userReviewDAO.findReviewByUserReviewId(userReviewId);
    }
}
