package ar.edu.itba.services;


import ar.edu.itba.paw.interfaces.Services.PostService;
import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.interfaces.Services.TransactionService;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:testPostService.sql")
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
    private static final Integer PRODUCT_QUANTITY = 15;
    private static final Integer BUYER_USER_ID = 87;
    private static final Integer POST_ID = 14;


    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private TransactionService transactionService;

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

    //TODO: Finish testMakeProductTransaction

    @Test
    public void testMakeProductTransaction() {
        final Integer state = transactionService.makeTransaction(BUYER_USER_ID, POST_ID, PRODUCT_QUANTITY);

    }
}
