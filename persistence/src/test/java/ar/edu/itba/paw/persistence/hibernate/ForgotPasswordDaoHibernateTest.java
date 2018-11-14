package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.ForgotPassword;
import ar.edu.itba.paw.models.User;
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
import static org.junit.Assert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:testForgottenPasswords.sql")
public class ForgotPasswordDaoHibernateTest {

    private static final int USER_ID = 8888;
    private static final String REQUEST_DATE = "01-01-2001";
    private static final String CODE = "code";
    private static final String INEXISTANT_CODE = "inexistant code";

    @Autowired
    private ForgotPasswordDaoHibernate forgotPasswordDaoHibernate;

    @PersistenceContext
    EntityManager em;

    @Before
    public void setUp() {
        em.createNativeQuery("DELETE FROM forgotpasswords");
        em.flush();
    }

    @Test
    public void testCreateNewRequest() {
        final ForgotPassword fpass = forgotPasswordDaoHibernate.createNewRequest(USER_ID, REQUEST_DATE, CODE);

        assertNotNull(fpass);
        assertEquals(USER_ID, fpass.getUserForgot().getUserId().intValue());
        assertEquals(REQUEST_DATE, fpass.getRequestDate());
        assertEquals(CODE, fpass.getCode());
        assertTrue(em.contains(fpass));
    }

    @Test
    public void testFindUserByCode() {
        final Optional<User> user = forgotPasswordDaoHibernate.findUserByCode(CODE);

        assertTrue(user.isPresent());
    }

    @Test
    public void testCheckCodeDoesNotExist() {
        final boolean codeDoesNotExist = forgotPasswordDaoHibernate.checkCodeDoesNotExist(INEXISTANT_CODE);
        final boolean codeDoesExist = forgotPasswordDaoHibernate.checkCodeDoesNotExist(CODE);

        assertTrue(codeDoesNotExist);
        assertFalse(codeDoesExist);
    }

    @Test
    public void testFindRequestByCode() {
        final Optional<ForgotPassword> fpass = forgotPasswordDaoHibernate.findRequestByCode(CODE);

        assertTrue(fpass.isPresent());
        assertEquals(CODE, fpass.get().getCode());
    }
}
