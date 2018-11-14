package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.UserReview;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:testUserReviews.sql")
public class UserReviewDaoHibernateTest {

    // The dummy user ID is the ID set in the first per-inserted user in the testUsers.sql script
    private static final int REVIEW_ID = 9999;
    private static final int REVIEWED_USER_ID = 8888;
    private static final int REVIEWER_ID = 7777;
    private static final int RATING = 4;
    private static final String DESCRIPTION = "This is my review";

    @Autowired
    private UserReviewDaoHibernate userReviewDao;

    @PersistenceContext
    EntityManager em;

    @Before
    public void setUp() {
        em.createNativeQuery("DELETE FROM userreviews");
        em.flush();
    }

    @Test
    public void testCreateUserReview() {
        final UserReview userReview = userReviewDao.createUserReview(REVIEWED_USER_ID, REVIEWER_ID, RATING, DESCRIPTION);

        assertNotNull(userReview);
        assertEquals(REVIEWED_USER_ID, userReview.getReviewedUser().getUserId().intValue());
        assertEquals(REVIEWER_ID, userReview.getReviewer().getUserId().intValue());
        assertEquals(RATING, userReview.getRating().intValue());
        assertEquals(DESCRIPTION, userReview.getDescription());
        assertTrue(em.contains(userReview));
    }

    @Test
    public void testFindReviewsByReviewedUserI() {
        final List<UserReview> reviews = userReviewDao.findReviewsByReviewedUserId(REVIEWED_USER_ID);

        assertFalse(reviews.isEmpty());

        for (UserReview review : reviews)
            assertEquals(REVIEWED_USER_ID, review.getReviewedUser().getUserId().intValue());
    }

    @Test
    public void testFindReviewsByReviewerId() {
        final List<UserReview> reviews = userReviewDao.findReviewsByReviewerId(REVIEWER_ID);

        assertFalse(reviews.isEmpty());

        for (UserReview review : reviews)
            assertEquals(REVIEWER_ID, review.getReviewer().getUserId().intValue());
    }

    @Test
    public void testFindReviewByUserReviewId() {
        final Optional<UserReview> review = userReviewDao.findReviewByUserReviewId(REVIEW_ID);

        assertTrue(review.isPresent());
        assertEquals(REVIEW_ID, review.get().getUserReviewId().intValue());
    }
}
