package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.UserDAO;
import ar.edu.itba.paw.models.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")

public class ProductDaoJDBCTest {

    private static final String PRODUCTNAME = "nameProduct";
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
    private static final String PRODUCTNAMEUPDATE = "nameeProduct";
    private static final String BRANDUPDATE = "brandd";
    private static final String RAMUPDATE = "ramm";
    private static final String STORAGEUPDATE = "storaage";
    private static final String OPERATIVESYSTEMUPDATE = "systemm";
    private static final String PROCESSORUPDATE = "processorr";
    private static final String BODYSIZEUPDATE = "10.6 p";
    private static final String SCREENSIZEUPDATE = "6 p";
    private static final String SCREENRATIOUPDATE = "42 p";
    private static final String REARCAMERAUPDATE = "12 mpx";
    private static final String FRONTCAMERAUPDATE = "5 mpx";

    @Autowired
    private DataSource ds;

    @Autowired
    private ProductDaoJDBC productDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @After
    public void cleanTables(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "products");
    }

    @Test
    public void testProductCreate(){
        final Product product = productDao.createProduct(PRODUCTNAME, BRAND, RAM, STORAGE, OPERATIVESYSTEM, PROCESSOR,
                BODYSIZE, SCREENSIZE, SCREENRATIO, REARCAMERA, FRONTCAMERA);

        assertNotNull(product);
        assertEquals(PRODUCTNAME, product.getProductName());
        assertEquals(BRAND, product.getBrand());
        assertEquals(RAM, product.getRam());
        assertEquals(STORAGE, product.getStorage());
        assertEquals(OPERATIVESYSTEM, product.getOperativeSystem());
        assertEquals(PROCESSOR, product.getProcessor());
        assertEquals(BODYSIZE, product.getBodySize());
        assertEquals(SCREENSIZE, product.getScreenSize());
        assertEquals(SCREENRATIO, product.getScreenRatio());
        assertEquals(REARCAMERA, product.getRearCamera());
        assertEquals(FRONTCAMERA, product.getFrontCamera());
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "products"));
    }

    @Test
    public void testProductUpdate(){
        Product product = productDao.createProduct(PRODUCTNAME, BRAND, RAM, STORAGE, OPERATIVESYSTEM, PROCESSOR,
                BODYSIZE, SCREENSIZE, SCREENRATIO, REARCAMERA, FRONTCAMERA);

        product = productDao.updateProduct(product.getProductId(), PRODUCTNAMEUPDATE, BRANDUPDATE, RAMUPDATE, STORAGEUPDATE,
                OPERATIVESYSTEMUPDATE, PROCESSORUPDATE, BODYSIZEUPDATE, SCREENSIZEUPDATE, SCREENRATIOUPDATE, REARCAMERAUPDATE,
                FRONTCAMERAUPDATE);

        assertNotNull(product);
        assertNotNull(product);
        assertEquals(PRODUCTNAMEUPDATE, product.getProductName());
        assertEquals(BRANDUPDATE, product.getBrand());
        assertEquals(RAMUPDATE, product.getRam());
        assertEquals(STORAGEUPDATE, product.getStorage());
        assertEquals(OPERATIVESYSTEMUPDATE, product.getOperativeSystem());
        assertEquals(PROCESSORUPDATE, product.getProcessor());
        assertEquals(BODYSIZEUPDATE, product.getBodySize());
        assertEquals(SCREENSIZEUPDATE, product.getScreenSize());
        assertEquals(SCREENRATIOUPDATE, product.getScreenRatio());
        assertEquals(REARCAMERAUPDATE, product.getRearCamera());
        assertEquals(FRONTCAMERAUPDATE, product.getFrontCamera());
    }

    @Test
    public void testProductFind(){
        final Product product = productDao.createProduct(PRODUCTNAME, BRAND, RAM, STORAGE, OPERATIVESYSTEM, PROCESSOR,
                BODYSIZE, SCREENSIZE, SCREENRATIO, REARCAMERA, FRONTCAMERA);

        final Product productFound = productDao.findProductByProductId(product.getProductId());
        assertNotNull(productFound);
        assertEquals(product.getProductId(), productFound.getProductId());
    }

    @Test
    public void testProductDelete(){
        final Product product = productDao.createProduct(PRODUCTNAME, BRAND, RAM, STORAGE, OPERATIVESYSTEM, PROCESSOR,
                BODYSIZE, SCREENSIZE, SCREENRATIO, REARCAMERA, FRONTCAMERA);

        assertTrue(productDao.deleteProduct(product.getProductId()));
        assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "products"));
    }
}
