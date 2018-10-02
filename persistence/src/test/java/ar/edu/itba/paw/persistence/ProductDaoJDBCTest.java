package ar.edu.itba.paw.persistence;


import ar.edu.itba.paw.models.Product;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:testProducts.sql")
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

    private static final String BRAND_ATTRIBUTE = "brand";
    private static final String BRAND_ATTRIBUTE_VALUE = "Samsung";
    private static final String OS_ATTRIBUTE = "operativeSystem";
    private static final String OS_ATTRIBUTE_VALUE = "Android";
    private static final String OPERATIVE_SYSTEMS[] = {"Android", "iOS"};

    // The amount of pre-inserted products in the testProducts.sql script
    private static final int ROWS_PRE_INSERTED = 9;
    // The dummy product ID is the ID set in the first per-inserted product in the testProducts.sql script
    private static final int DUMMY_PRODUCT_ID = 9999;

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
        // There pre-inserted products in the testProducts script, plus the one created in this test
        assertEquals(ROWS_PRE_INSERTED + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "products"));
    }

    @Test
    public void testProductUpdate(){
        Optional<Product> product = productDao.updateProduct(DUMMY_PRODUCT_ID, PRODUCTNAMEUPDATE, BRANDUPDATE, RAMUPDATE, STORAGEUPDATE,
                OPERATIVESYSTEMUPDATE, PROCESSORUPDATE, BODYSIZEUPDATE, SCREENSIZEUPDATE, SCREENRATIOUPDATE, REARCAMERAUPDATE,
                FRONTCAMERAUPDATE);

        assertTrue(product.isPresent());
        assertEquals(PRODUCTNAMEUPDATE, product.get().getProductName());
        assertEquals(BRANDUPDATE, product.get().getBrand());
        assertEquals(RAMUPDATE, product.get().getRam());
        assertEquals(STORAGEUPDATE, product.get().getStorage());
        assertEquals(OPERATIVESYSTEMUPDATE, product.get().getOperativeSystem());
        assertEquals(PROCESSORUPDATE, product.get().getProcessor());
        assertEquals(BODYSIZEUPDATE, product.get().getBodySize());
        assertEquals(SCREENSIZEUPDATE, product.get().getScreenSize());
        assertEquals(SCREENRATIOUPDATE, product.get().getScreenRatio());
        assertEquals(REARCAMERAUPDATE, product.get().getRearCamera());
        assertEquals(FRONTCAMERAUPDATE, product.get().getFrontCamera());
        assertEquals(ROWS_PRE_INSERTED, JdbcTestUtils.countRowsInTable(jdbcTemplate, "products"));
    }

    @Test
    public void testProductFind(){
        final Optional<Product> productFound = productDao.findProductByProductId(DUMMY_PRODUCT_ID);
        assertTrue(productFound.isPresent());
        assertEquals(DUMMY_PRODUCT_ID, productFound.get().getProductId().intValue());
    }

    @Test
    public void testProductDelete(){
        assertTrue(productDao.deleteProduct(DUMMY_PRODUCT_ID));
        assertEquals(ROWS_PRE_INSERTED - 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "products"));
    }

    @Test
    public void testFilterProductsByBrand() {
        String attributes[] = {BRAND_ATTRIBUTE};
        String attributeValues[] = {BRAND_ATTRIBUTE_VALUE};

        List<Product> products = productDao.filterProducts(attributes.length, attributes, attributeValues);
        assertNotNull(products);

        for (Product product : products)
            assertEquals(attributeValues[0], product.getBrand());
    }

    @Test
    public void testFilterProductsByOperativeSystem() {
        String attributes[] = {OS_ATTRIBUTE};
        String attributeValues[] = {OS_ATTRIBUTE_VALUE};

        List<Product> products = productDao.filterProducts(attributes.length, attributes, attributeValues);
        assertNotNull(products);

        for (Product product : products)
            assertEquals(attributeValues[0], product.getOperativeSystem());
    }

    @Test
    public void testFindAllAttributeValuesForOperativeSystemFilter() {
        final List<String> operativeSystemValues = productDao.findAllAttributeValuesForFilter(OS_ATTRIBUTE);
        assertNotNull(operativeSystemValues);

        for (String operativeSystem : operativeSystemValues)
            assertTrue(Arrays.asList(OPERATIVE_SYSTEMS).contains(operativeSystem));
    }
}
