package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.DAO.UserReviewDAO;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.UserReview;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserReviewDaoHibernate implements UserReviewDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserReviewDaoHibernate.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public UserReview createUserReview(Integer reviewedUserId, Integer reviewerId, Integer rating, String description) {
        User reviewedUser = em.find(User.class, reviewedUserId);
        User reviewer = em.find(User.class, reviewerId);
        UserReview userReview = new UserReview(reviewedUser, reviewer, rating, description);
        em.persist(userReview);
        LOGGER.info("UserReview inserted with userReviewId = {}", userReview.getUserReviewId().intValue());
        return userReview;
    }

    @Transactional
    @Override
    public List<UserReview> findReviewsByReviewedUserId(Integer reviewedUserId){
        User reviewedUser = em.find(User.class, reviewedUserId);
        Hibernate.initialize(reviewedUser.getReviewedUserList());
        return reviewedUser.getReviewedUserList();
    }

    @Transactional
    @Override
    public List<UserReview> findReviewsByReviewerId(Integer reviewerId){
        User reviewer = em.find(User.class, reviewerId);
        Hibernate.initialize(reviewer.getReviewerList());
        return reviewer.getReviewerList();
    }

    @Override
    public Optional<UserReview> findReviewByUserReviewId(Integer userReviewId){
        return Optional.ofNullable(em.find(UserReview.class, userReviewId));
    }
}
