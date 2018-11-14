package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.Admin;
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
import static org.junit.Assert.assertNotNull;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:testAdmins.sql")
public class AdminDaoHibernateTest {

    private static final int USER_ID = 9999;


    @Autowired
    private AdminDaoHibernate adminDaoHibernate;

    @PersistenceContext
    EntityManager em;

    @Before
    public void setUp() {
        em.createNativeQuery("DELETE FROM admins");
        em.flush();
    }

    @Test
    public void testCreateAdmin() {
        final Admin admin = adminDaoHibernate.createAdmin(USER_ID);

        assertNotNull(admin);
        assertEquals(USER_ID, admin.getUserAdmin().getUserId().intValue());
        assertTrue(em.contains(admin));
    }

    @Test
    public void testFindAdminByUserId() {
        final Optional<Admin> admin = adminDaoHibernate.findAdminByUserId(USER_ID);

        assertTrue(admin.isPresent());
        assertEquals(USER_ID, admin.get().getUserAdmin().getUserId().intValue());
    }

    @Test
    public void testIsAdmin() {
        final boolean check = adminDaoHibernate.isAdmin(USER_ID);

        assertTrue(check);
    }
}
