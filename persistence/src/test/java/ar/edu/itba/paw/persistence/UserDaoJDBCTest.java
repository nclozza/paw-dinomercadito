package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.User;
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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")
public class UserDaoJDBCTest {
    private static final String PASSWORD = "Password";
    private static final String USERNAME = "Username";
    private static final String EMAIL = "Email";
    private static final String PHONE = "123456";
    private static final String BIRTHDATE = "1995-09-01";
    private static final String USERNAMEUPDATE = "UsernameUpdate";
    private static final String PASSWORDUPDATE = "123";
    private static final String EMAILUPDATE = "mail";
    private static final String PHONEUPDATE = "13579";
    private static final String BIRTHDATEUPDATE = "1996-09-01";

    @Autowired
    private DataSource ds;

    @Autowired
    private UserDaoJDBC userDao;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
    }

    @Test
    public void testUserCreate() {
        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE);
        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PHONE, user.getPhone());
        assertEquals(BIRTHDATE, user.getBirthdate());
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }

    @Test
    public void testUserUpdate(){
        final User user = userDao.createUser(USERNAMEUPDATE, PASSWORD, EMAIL, PHONE, BIRTHDATE);

        final User userUpdate = userDao.updateUser(user.getUserId(), PASSWORDUPDATE, EMAILUPDATE, PHONEUPDATE, BIRTHDATEUPDATE);
        assertNotNull(userUpdate);
        assertEquals(PASSWORDUPDATE, userUpdate.getPassword());
        assertEquals(EMAILUPDATE, userUpdate.getEmail());
        assertEquals(PHONEUPDATE, userUpdate.getPhone());
        assertEquals(BIRTHDATEUPDATE, userUpdate.getBirthdate());
    }

}
