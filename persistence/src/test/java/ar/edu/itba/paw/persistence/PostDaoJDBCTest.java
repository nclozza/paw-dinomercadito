package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.PostDAO;
import ar.edu.itba.paw.interfaces.DAO.ProductDAO;
import ar.edu.itba.paw.interfaces.DAO.UserDAO;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

import java.util.List;

import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")

public class PostDaoJDBCTest {

    private static final String PASSWORD = "Password";
    private static final String USERNAME = "UsernamePost";
    private static final String EMAIL = "Email";
    private static final String PHONE = "123456";
    private static final String BIRTHDATE = "1995-09-01";
    private static final Double FUNDS = 0.0;
    private static final String PRODUCTNAME = "namePost";
    private static final String PRODUCTNAMEUPDATE = "nameePost";
    private static final String BRAND = "brand";
    private static final String RAM = "ram";
    private static final String STORAGE = "storage";
    private static final String OPERATIVESYSTEM = "system";
    private static final String PROCESSOR = "processor";
    private static final String BODYSIZE = "10.5 p";
    private static final String SCREENSIZE = "5 p";
    private static final String SCREENRATIO = "40 p";
    private static final String REARCAMERA = "16 mpx";
    private static final String FRONTCAMERA = "8 mpx";
    private static final Double PRICE = 10000.50;
    private static final String DESCRIPTION = "esta es la descripcion";
    private static final Double PRICEUPDATE = 15000.50;
    private static final String DESCRIPTIONUPDATE = "esta es la segunda descripcion";
    private static final Integer PRODUCTQUANTITY = 15;
    private static final Integer PRODUCTQUANTITYUPDATE = 500;

    @Autowired
    private DataSource ds;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private ProductDAO productDao;

    @Autowired
    private PostDAO postDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @After
    public void cleanTables(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "posts");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "products");
    }

    @Test
    public void testPostCreate(){
        final Product product = productDao.createProduct(PRODUCTNAME, BRAND, RAM, STORAGE, OPERATIVESYSTEM, PROCESSOR,
                BODYSIZE, SCREENSIZE, SCREENRATIO, REARCAMERA, FRONTCAMERA);
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE, FUNDS);

        final Post post = postDao.createPost(product.getProductId(), PRICE, user.getUserId(), DESCRIPTION,
                PRODUCTQUANTITY);

        assertNotNull(post);
        assertEquals(product.getProductId(), post.getProductId());
        assertEquals(user.getUserId(), post.getUserId());
        assertEquals(PRICE, post.getPrice());
        assertEquals(DESCRIPTION, post.getDescription());
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "posts"));
        assertEquals(PRODUCTQUANTITY, post.getProductQuantity());
    }

    @Test
    public void testPostUpdate(){
        final Product product = productDao.createProduct(PRODUCTNAME, BRAND, RAM, STORAGE, OPERATIVESYSTEM, PROCESSOR,
                BODYSIZE, SCREENSIZE, SCREENRATIO, REARCAMERA, FRONTCAMERA);
        final Product productUpdate = productDao.createProduct(PRODUCTNAMEUPDATE, BRAND, RAM, STORAGE, OPERATIVESYSTEM, PROCESSOR,
                BODYSIZE, SCREENSIZE, SCREENRATIO, REARCAMERA, FRONTCAMERA);
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE, FUNDS);
        Post post = postDao.createPost(product.getProductId(), PRICE, user.getUserId(), DESCRIPTION, PRODUCTQUANTITY);

        post = postDao.updatePost(post.getPostId(), productUpdate.getProductId(), PRICEUPDATE, DESCRIPTIONUPDATE,
                PRODUCTQUANTITYUPDATE);
        assertNotNull(post);
        assertEquals(productUpdate.getProductId(), post.getProductId());
        assertEquals(PRICEUPDATE, post.getPrice());
        assertEquals(DESCRIPTIONUPDATE, post.getDescription());
        assertEquals(PRODUCTQUANTITYUPDATE, post.getProductQuantity());
    }

    @Test
    public void testPostFindByPost(){
        final Product product = productDao.createProduct(PRODUCTNAME, BRAND, RAM, STORAGE, OPERATIVESYSTEM, PROCESSOR,
                BODYSIZE, SCREENSIZE, SCREENRATIO, REARCAMERA, FRONTCAMERA);
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE, FUNDS);
        final Post post = postDao.createPost(product.getProductId(), PRICE, user.getUserId(), DESCRIPTION,
                PRODUCTQUANTITY);

        final Post postFound = postDao.findPostByPostId(post.getPostId());
        assertNotNull(postFound);
        assertEquals(post.getPostId(), postFound.getPostId());
    }

    @Test
    public void testPostFindByUser(){
        final Product product = productDao.createProduct(PRODUCTNAME, BRAND, RAM, STORAGE, OPERATIVESYSTEM, PROCESSOR,
                BODYSIZE, SCREENSIZE, SCREENRATIO, REARCAMERA, FRONTCAMERA);
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE, FUNDS);
        final Post post = postDao.createPost(product.getProductId(), PRICE, user.getUserId(), DESCRIPTION,
                PRODUCTQUANTITY);

        final List<Post> postList = postDao.findPostsByUserId(post.getUserId());
        assertNotNull(postList);
        assertEquals(post.getPostId(), postList.get(0).getPostId());
    }

    @Test
    public void testPostDelete(){
        final Product product = productDao.createProduct(PRODUCTNAME, BRAND, RAM, STORAGE, OPERATIVESYSTEM, PROCESSOR,
                BODYSIZE, SCREENSIZE, SCREENRATIO, REARCAMERA, FRONTCAMERA);
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE, FUNDS);
        final Post post = postDao.createPost(product.getProductId(), PRICE, user.getUserId(), DESCRIPTION,
                PRODUCTQUANTITY);

        assertTrue(postDao.deletePost(post.getPostId()));
        assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "posts"));
    }
}
