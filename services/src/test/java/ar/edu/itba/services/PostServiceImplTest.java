package ar.edu.itba.services;

import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.interfaces.Services.UserService;
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

import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:../../../../../persistence/src/test/resources/schema.sql")

public class PostServiceImplTest {

    private static final String PASSWORD_SELLER = "seller-pass";
    private static final String USERNAME_SELLER = "seller-test";
    private static final String EMAIL_SELLER = "seller@test.com";
    private static final String PHONE_SELLER = "123456";
    private static final String BIRTHDATE_SELLER = "1995-09-02";
    private static final Double FUNDS_SELLER = 125000.0;
    private static final String PASSWORD_BUYER = "buyer-pass";
    private static final String USERNAME_BUYER = "buyer-test";
    private static final String EMAIL_BUYER = "buyer@test.com";
    private static final String PHONE_BUYER = "7891011";
    private static final String BIRTHDATE_BUYER = "1995-09-03";
    private static final Double FUNDS_BUYER = 125000.0;

    private static final String PRODUCTNAME = "namePost";
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
    private static final Integer PRODUCTQUANTITY = 15;


    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private DataSource ds;

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
    public void testMakeProductTransaction() {
        final Product product = productService.createProduct(PRODUCTNAME, BRAND, RAM, STORAGE, OPERATIVESYSTEM, PROCESSOR,
                BODYSIZE, SCREENSIZE, SCREENRATIO, REARCAMERA, FRONTCAMERA);
        User buyer = userService.createUser(USERNAME_BUYER, PASSWORD_BUYER, EMAIL_BUYER, PHONE_BUYER, BIRTHDATE_BUYER,
                FUNDS_BUYER);
        User seller = userService.createUser(USERNAME_SELLER, PASSWORD_SELLER, EMAIL_SELLER, PHONE_SELLER, BIRTHDATE_SELLER,
                FUNDS_SELLER);
        final Post post = postService.createPost(product.getProductId(), PRICE, seller.getUserId(), DESCRIPTION,
                PRODUCTQUANTITY);

        assertTrue(postService.makeProductTransaction(buyer.getUserId(), post.getPostId()));

        Double newBuyerFunds = userService.findUserByUserId(buyer.getUserId()).getFunds();
        Double newSellerFunds = userService.findUserByUserId(seller.getUserId()).getFunds();

        assertEquals(FUNDS_BUYER - PRICE, newBuyerFunds);
        assertEquals(FUNDS_SELLER + PRICE, newSellerFunds);

        Integer newProductQuantity = postService.findPostByPostId(post.getPostId()).getProductQuantity();

        assertEquals(PRODUCTQUANTITY - 1, (int)newProductQuantity);
    }
}
