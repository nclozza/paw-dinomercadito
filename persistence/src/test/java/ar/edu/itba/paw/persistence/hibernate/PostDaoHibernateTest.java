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
    private static final Integer PRODUCTQUANTITY = 5;
    private static final int VISITS = 1;
    private static final Integer PRODUCTQUANTITYUPDATE = 500;
    private static final Integer VISITSUPDATE = 20;
    private static final String FILTER = "iPhone";

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
    public void testCreatePost() {
        final Post post = postDao.createPost(DUMMY_PRODUCTID, PRICE, DUMMY_USERID, DESCRIPTION, PRODUCTQUANTITY,
                VISITS);

        assertNotNull(post);
        assertEquals(DUMMY_PRODUCTID, post.getProductPosted().getProductId().intValue());
        assertEquals(DUMMY_USERID, post.getUserSeller().getUserId().intValue());
        assertEquals(PRICE, post.getPrice());
        assertEquals(DESCRIPTION, post.getDescription());
        assertEquals(PRODUCTQUANTITY, post.getProductQuantity());
        assertEquals(VISITS, post.getVisits().intValue());
        assertTrue(em.contains(post));
    }

    @Test
    public void testDeletePost() {
        assertTrue(postDao.deletePost(DUMMY_POSTID));
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
    public void testUpdatePostProductQuantity() {
        final Optional<Post> post = postDao.updatePostProductQuantity(DUMMY_POSTID, PRODUCTQUANTITYUPDATE);

        assertTrue(post.isPresent());
        assertEquals(DUMMY_POSTID, post.get().getPostId().intValue());
        assertEquals(PRODUCTQUANTITYUPDATE, post.get().getProductQuantity());
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
    public void testFindMostVisitedPosts() {
        final List<Post> postList = postDao.findMostVisitedPosts();

        assertFalse(postList.isEmpty());
    }

    @Test
    public void testFindPostsByFilter() {
        final List<Post> postList = postDao.findPostsByFilter(FILTER);
        boolean filterMatchesDescription, filterMatchesProductname, filterMatchesUsername;

        assertFalse(postList.isEmpty());

        for (Post p : postList) {
            filterMatchesDescription = p.getDescription().toLowerCase().contains(FILTER.toLowerCase());
            filterMatchesProductname = p.getProductPosted().getProductName().toLowerCase().contains(FILTER.toLowerCase());
            filterMatchesUsername = p.getUserSeller().getUsername().toLowerCase().contains(FILTER.toLowerCase());
            assertTrue(filterMatchesDescription || filterMatchesProductname || filterMatchesUsername);
        }
    }

    @Test
    public void testFindAllPosts() {
        final List<Post> postList = postDao.findAllPosts();

        assertFalse(postList.isEmpty());
    }

    @Test
    public void testAddVisit() {
        final Optional<Post> post = postDao.addVisit(DUMMY_POSTID);

        assertTrue(post.isPresent());
        assertEquals(DUMMY_POSTID, post.get().getPostId().intValue());
        assertEquals(VISITS + 1, post.get().getVisits().intValue());
    }
}
