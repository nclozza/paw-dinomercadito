package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.PostDAO;
import ar.edu.itba.paw.models.Post;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


//@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:testPosts.sql")
public class PostDaoJDBCTest {

    private static final Double PRICE = 10000.50;
    private static final String DESCRIPTION = "esta es la descripcion";
    private static final Double PRICEUPDATE = 15000.50;
    private static final String DESCRIPTIONUPDATE = "esta es la segunda descripcion";
    private static final Integer PRODUCTQUANTITY = 15;
    private static final Integer PRODUCTQUANTITYUPDATE = 500;

    // The amount of pre-inserted posts in the testPosts.sql script
    private static final int ROWS_PRE_INSERTED = 4;
    // The dummy user ID is the ID set in the first per-inserted post in the testPosts.sql script
    private static final int DUMMY_USERID = 99997;
    // The dummy product ID is the ID set in the first per-inserted post in the testPosts.sql script
    private static final int DUMMY_PRODUCTID = 99998;
    // The dummy post ID is the ID set in the first per-inserted post in the testPosts.sql script
    private static final int DUMMY_POSTID = 99999;


    @Autowired
    private DataSource ds;

    @Autowired
    private PostDAO postDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @After
    public void cleanTables() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "posts", "products", "users");
    }


    @Test
    public void testPostCreate() {
        final Post post = postDao.createPost(DUMMY_PRODUCTID, PRICE, DUMMY_USERID, DESCRIPTION, PRODUCTQUANTITY);

        assertNotNull(post);
        assertEquals(DUMMY_PRODUCTID, post.getProductId().intValue());
        assertEquals(DUMMY_USERID, post.getUserId().intValue());
        assertEquals(PRICE, post.getPrice());
        assertEquals(DESCRIPTION, post.getDescription());
        assertEquals(PRODUCTQUANTITY, post.getProductQuantity());
        assertEquals(ROWS_PRE_INSERTED + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "posts"));
    }

    @Test
    public void testUpdatePost() {
        Optional<Post> post = postDao.updatePost(DUMMY_POSTID, DUMMY_PRODUCTID, PRICEUPDATE, DESCRIPTIONUPDATE,
                PRODUCTQUANTITYUPDATE);

        assertTrue(post.isPresent());
        assertEquals(DUMMY_PRODUCTID, post.get().getProductId().intValue());
        assertEquals(PRICEUPDATE, post.get().getPrice());
        assertEquals(DESCRIPTIONUPDATE, post.get().getDescription());
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

        assertTrue(!postList.isEmpty());

        for (Post post : postList)
            assertEquals(DUMMY_USERID, post.getUserId().intValue());
    }

    @Test
    public void testFindPostsByProductId() {
        final List<Post> postList = postDao.findPostsByProductId(DUMMY_PRODUCTID);

        assertTrue(!postList.isEmpty());

        for (Post post : postList)
            assertEquals(DUMMY_PRODUCTID, post.getProductId().intValue());
    }

    @Test
    public void testDeletePost() {
        assertTrue(postDao.deletePost(DUMMY_POSTID));
        assertEquals(ROWS_PRE_INSERTED - 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "posts"));
    }
}
