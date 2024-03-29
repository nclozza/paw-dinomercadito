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
    public UserReview createUserReview(Integer userReviewedId, Integer userWhoReviewId, Integer rating, String description) {
        User userReviewed = em.find(User.class, userReviewedId);
        User userWhoReview = em.find(User.class, userWhoReviewId);
        UserReview userReview = new UserReview(userReviewed, userWhoReview, rating, description);
        em.persist(userReview);
        LOGGER.info("UserReview inserted with userReviewId = {}", userReview.getUserReviewId().intValue());
        return userReview;
    }

    @Transactional
    @Override
    public List<UserReview> findReviewsByUserReviewedId(Integer userReviewedId){
        User userReviewed = em.find(User.class, userReviewedId);
        Hibernate.initialize(userReviewed.getUserReviewedList());
        return userReviewed.getUserReviewedList();
    }

    @Transactional
    @Override
    public List<UserReview> findReviewsByUserWhoReviewId(Integer userWhoReviewId){
        User userWhoReview = em.find(User.class, userWhoReviewId);
        Hibernate.initialize(userWhoReview.getUserWhoReviewList());
        return userWhoReview.getUserWhoReviewList();
    }

    @Override
    public Optional<UserReview> findReviewByUserReviewId(Integer userReviewId){
        return Optional.ofNullable(em.find(UserReview.class, userReviewId));
    }
}
