package ar.edu.itba.paw.persistence;

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

public class UserDaoJDBCTest {

    private static final String PASSWORD = "Password";
    private static final String USERNAME = "UsernameUser";
    private static final String EMAIL = "Email";
    private static final String PHONE = "123456";
    private static final String BIRTHDATE = "1995-09-01";
    private static final String PASSWORDUPDATE = "123";
    private static final String EMAILUPDATE = "mail";
    private static final String PHONEUPDATE = "13579";
    private static final String BIRTHDATEUPDATE = "1996-09-01";
    private static final Double FUNDS = 20000.0;
    private static final Double FUNDSUPDATE = 40000.0;

    @Autowired
    private DataSource ds;

    @Autowired
    private UserDaoJDBC userDao;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @After
    public void cleanTables(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
    }

    @Test
    public void testUserCreate() {
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE, FUNDS);

        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PHONE, user.getPhone());
        assertEquals(BIRTHDATE, user.getBirthdate());
        assertEquals(FUNDS, user.getFunds());
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }

    @Test
    public void testUserUpdate() {
        User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE);

        user = userDao.updateUser(user.getUserId(), PASSWORDUPDATE, EMAILUPDATE, PHONEUPDATE, BIRTHDATEUPDATE,
                FUNDSUPDATE);
        assertNotNull(user);
        assertEquals(PASSWORDUPDATE, user.getPassword());
        assertEquals(EMAILUPDATE, user.getEmail());
        assertEquals(PHONEUPDATE, user.getPhone());
        assertEquals(BIRTHDATEUPDATE, user.getBirthdate());
        assertEquals(FUNDSUPDATE, user.getFunds());
    }

    @Test
    public void testUserFind() {
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE);

        final User userFound = userDao.findUserByUserId(user.getUserId());
        assertNotNull(userFound);
        assertEquals(user.getUserId(), userFound.getUserId());
    }

    @Test
    public void testUserDelete() {
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE);

        assertTrue(userDao.deleteUser(user.getUserId()));
        assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }
}
