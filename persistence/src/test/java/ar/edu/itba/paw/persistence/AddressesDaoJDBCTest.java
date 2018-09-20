package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.UserDAO;
import ar.edu.itba.paw.models.Address;
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
@Sql("classpath:schema.sql")

public class AddressesDaoJDBCTest {

    private static final String STREET = "calleAddre";
    private static final Integer NUMBER = 34;
    private static final String CITY = "ciudad";
    private static final String PROVINCE = "provincia";
    private static final String ZIPCODE = "12A4";
    private static final String COUNTRY = "pais";
    private static final String STREETUPDATE = "callee";
    private static final Integer NUMBERUPDATE = 35;
    private static final String CITYUPDATE = "ciudadd";
    private static final String PROVINCEUPDATE = "provinciaa";
    private static final String ZIPCODEUPDATE = "12A5";
    private static final String COUNTRYUPDATE = "paiss";
    private static final String PASSWORD = "Password";
    private static final String USERNAME = "UsernameAddress";
    private static final String EMAIL = "Email";
    private static final String PHONE = "123456";
    private static final String BIRTHDATE = "1995-09-01";
    private static final Double FUNDS = 0.0;

    @Autowired
    private DataSource ds;

    @Autowired
    private AddressDaoJDBC addressDao;

    @Autowired
    private UserDAO userDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @After
    public void cleanTables(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "addresses");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
    }

    @Test
    public void testAddressCreate() {
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE, FUNDS);

        final Address address = addressDao.createAddress(user.getUserId(), STREET, NUMBER, CITY, PROVINCE, ZIPCODE, COUNTRY);

        assertNotNull(address);
        assertEquals(STREET, address.getStreet());
        assertEquals(NUMBER, address.getNumber());
        assertEquals(CITY, address.getCity());
        assertEquals(PROVINCE, address.getProvince());
        assertEquals(ZIPCODE, address.getZipCode());
        assertEquals(COUNTRY, address.getCountry());
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "addresses"));
    }

    @Test
    public void testAddressUpdate(){
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE, FUNDS);
        Address address = addressDao.createAddress(user.getUserId(), STREET, NUMBER, CITY, PROVINCE, ZIPCODE, COUNTRY);
        address = addressDao.updateAddress(address.getAddressId(), STREETUPDATE, NUMBERUPDATE, CITYUPDATE, PROVINCEUPDATE, ZIPCODEUPDATE, COUNTRYUPDATE);

        assertNotNull(address);
        assertEquals(STREETUPDATE, address.getStreet());
        assertEquals(NUMBERUPDATE, address.getNumber());
        assertEquals(CITYUPDATE, address.getCity());
        assertEquals(PROVINCEUPDATE, address.getProvince());
        assertEquals(ZIPCODEUPDATE, address.getZipCode());
        assertEquals(COUNTRYUPDATE, address.getCountry());
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "addresses"));
    }

    @Test
    public void testAddressFind(){
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE, FUNDS);
        final Address address = addressDao.createAddress(user.getUserId(), STREET, NUMBER, CITY, PROVINCE, ZIPCODE, COUNTRY);

        Address addressFound = addressDao.findAddressByAddressId(address.getAddressId());
        assertNotNull(addressFound);
        assertEquals(address.getAddressId(), addressFound.getAddressId());
    }

    @Test
    public void testAddressDelete(){
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE, FUNDS);
        final Address address = addressDao.createAddress(user.getUserId(), STREET, NUMBER, CITY, PROVINCE, ZIPCODE, COUNTRY);

        assertTrue(addressDao.deleteAddressByAddressId(address.getAddressId()));
        assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "addresses"));
    }
}

