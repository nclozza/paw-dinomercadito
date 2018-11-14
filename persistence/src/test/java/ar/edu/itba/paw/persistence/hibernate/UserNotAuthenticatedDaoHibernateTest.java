package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.UserNotAuthenticated;
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

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:testNotAuthenticatedUsers.sql")
public class UserNotAuthenticatedDaoHibernateTest {

    private static final String USERNAME = "dinolucas";
    private static final String PASSWORD = "lucaspass";
    private static final String EMAIL = "dinolucas@gmail.com";
    private static final String PHONE = "123456";
    private static final String BIRTHDATE = "02-02-2002";
    private static final String SIGNUP_DATE = "28-02-2002";
    private static final int CODE = 9876;
    private static final int INEXISTENT_CODE = 333333;
    private static final String INEXISTENT_EMAIL = "not_an_email_address@gmail.com";
    private static final String INEXISTENT_USERNAME = "not_a_username";
    private static final int USER_ID = 9999;

    @Autowired
    private UserNotAuthenticatedDaoHibernate userNotAuthDaoHibernate;

    @PersistenceContext
    EntityManager em;

    @Before
    public void setUp() {
        em.createNativeQuery("DELETE FROM usersnotauthenticated");
        em.flush();
    }

    @Test
    public void testCreateNotAuthenticatedUser() {
        final UserNotAuthenticated user = userNotAuthDaoHibernate.createUser(USERNAME, PASSWORD, EMAIL, PHONE,
                BIRTHDATE, SIGNUP_DATE, CODE);

        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PHONE, user.getPhone());
        assertEquals(BIRTHDATE, user.getBirthdate());
        assertEquals(SIGNUP_DATE, user.getSignUpDate());
        assertEquals(CODE, user.getCode().intValue());
        assertTrue(em.contains(user));
    }

    @Test
    public void testFindUserByUserId() {
        final Optional<UserNotAuthenticated> user = userNotAuthDaoHibernate.findUserByUserId(USER_ID);

        assertTrue(user.isPresent());
        assertEquals(USER_ID, user.get().getUserId().intValue());
    }

    @Test
    public void testFindUserByUsername() {
        final Optional<UserNotAuthenticated> user = userNotAuthDaoHibernate.findUserByUsername(USERNAME);

        assertTrue(user.isPresent());
        assertEquals(USERNAME, user.get().getUsername());
    }

    @Test
    public void testFindUserByCode() {
        final Optional<UserNotAuthenticated> user = userNotAuthDaoHibernate.findUserByCode(CODE);

        assertTrue(user.isPresent());
        assertEquals(CODE, user.get().getCode().intValue());
    }

    @Test
    public void testFindUserByEmail() {
        final Optional<UserNotAuthenticated> user = userNotAuthDaoHibernate.findUserByEmail(EMAIL);

        assertTrue(user.isPresent());
        assertEquals(EMAIL, user.get().getEmail());
    }

    @Test
    public void testDeleteUser() {
        final boolean deleted = userNotAuthDaoHibernate.deleteUser(USER_ID);
        assertTrue(deleted);
    }

    @Test
    public void testCheckCodeDoesNotExist() {
        final boolean codeDoesNotExist = userNotAuthDaoHibernate.checkCodeDoesNotExist(INEXISTENT_CODE);
        final boolean codeExists = userNotAuthDaoHibernate.checkCodeDoesNotExist(CODE);

        assertTrue(codeDoesNotExist);
        assertFalse(codeExists);
    }

    @Test
    public void testCheckEmail() {
        final boolean emailDoesNotExist = userNotAuthDaoHibernate.checkEmail(INEXISTENT_EMAIL);
        final boolean emailExists = userNotAuthDaoHibernate.checkEmail(EMAIL);

        assertTrue(emailDoesNotExist);
        assertFalse(emailExists);
    }

    @Test
    public void testCheckUsername() {
        final boolean usernameDoesNotExist = userNotAuthDaoHibernate.checkUsername(INEXISTENT_USERNAME);
        final boolean usernameExists = userNotAuthDaoHibernate.checkUsername(USERNAME);

        assertTrue(usernameDoesNotExist);
        assertFalse(usernameExists);
    }
}
