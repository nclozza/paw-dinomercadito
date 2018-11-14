package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Post;
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
@Sql("classpath:testPosts.sql")
public class PostDaoHibernateTest {

    private static final Double PRICE = 10000.50;
    private static final String DESCRIPTION = "esta es la descripcion";
    private static final Double PRICEUPDATE = 15000.50;
    private static final String DESCRIPTIONUPDATE = "esta es la segunda descripcion";
    private static final Integer PRODUCTQUANTITY = 15;
    private static final Integer PRODUCTQUANTITYUPDATE = 500;
    private static final Integer VISITS = 5;
    private static final Integer VISITSUPDATE = 20;

    // The dummy user ID is the ID set in the first per-inserted post in the testPosts.sql script
    private static final int DUMMY_USERID = 99997;
    // The dummy product ID is the ID set in the first per-inserted post in the testPosts.sql script
    private static final int DUMMY_PRODUCTID = 99998;
    // The dummy post ID is the ID set in the first per-inserted post in the testPosts.sql script
    private static final int DUMMY_POSTID = 99999;


    @Autowired
    private PostDaoHibernate postDao;

    @PersistenceContext
    EntityManager em;

    @Before
    public void setUp() {
        em.createNativeQuery("DELETE FROM posts");
        em.flush();
    }

    @Test
    public void testPostCreate() {
        final Post post = postDao.createPost(DUMMY_PRODUCTID, PRICE, DUMMY_USERID, DESCRIPTION, PRODUCTQUANTITY,
                VISITS);

        assertNotNull(post);
        assertEquals(DUMMY_PRODUCTID, post.getProductPosted().getProductId().intValue());
        assertEquals(DUMMY_USERID, post.getUserSeller().getUserId().intValue());
        assertEquals(PRICE, post.getPrice());
        assertEquals(DESCRIPTION, post.getDescription());
        assertEquals(PRODUCTQUANTITY, post.getProductQuantity());
        assertEquals(VISITS, post.getVisits());
        assertTrue(em.contains(post));
    }

    @Test
    public void testUpdatePost() {
        Optional<Post> post = postDao.updatePost(DUMMY_POSTID, DUMMY_PRODUCTID, PRICEUPDATE, DESCRIPTIONUPDATE,
                PRODUCTQUANTITYUPDATE, VISITSUPDATE);

        assertTrue(post.isPresent());
        assertEquals(DUMMY_PRODUCTID, post.get().getProductPosted().getProductId().intValue());
        assertEquals(PRICEUPDATE, post.get().getPrice());
        assertEquals(DESCRIPTIONUPDATE, post.get().getDescription());
        assertEquals(PRODUCTQUANTITYUPDATE, post.get().getProductQuantity());
        assertEquals(VISITSUPDATE, post.get().getVisits());
    }

    @Test
    public void testFindPostByPostId() {
        final Optional<Post> postFound = postDao.findPostByPostId(DUMMY_POSTID);
        assertTrue(postFound.isPresent());
        assertEquals(DUMMY_POSTID, postFound.get().getPostId().intValue());
    }

    @Test
    public void testFindPostsByUserId() {
        final List<Post> postList = postDao.findPostsByUserId(DUMMY_USERID);

        assertFalse(postList.isEmpty());

        for (Post post : postList)
            assertEquals(DUMMY_USERID, post.getUserSeller().getUserId().intValue());
    }

    @Test
    public void testFindPostsByProductId() {
        final List<Post> postList = postDao.findPostsByProductId(DUMMY_PRODUCTID);

        assertFalse(postList.isEmpty());

        for (Post post : postList)
            assertEquals(DUMMY_PRODUCTID, post.getProductPosted().getProductId().intValue());
    }

    @Test
    public void testDeletePost() {
        assertTrue(postDao.deletePost(DUMMY_POSTID));
    }
}
