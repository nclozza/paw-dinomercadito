//package ar.edu.itba.paw.persistence.hibernate;
//
//import ar.edu.itba.paw.models.User;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//
//
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
//@Sql("classpath:testUsers.sql")
//public class UserDaoHibernateTest {
//
//    private static final String USERNAME = "Username";
//    private static final String PASSWORD = "Password";
//    private static final String EMAIL = "Email";
//    private static final String PHONE = "123456";
//    private static final String BIRTHDATE = "02-02-2002";
//    private static final String PASSWORDUPDATE = "123";
//    private static final String EMAILUPDATE = "mail";
//    private static final String PHONEUPDATE = "13579";
//    private static final String BIRTHDATEUPDATE = "28-02-2002";
//
//    private static final String DUMMY_USERNAME = "dinolucas";
//    // The dummy user ID is the ID set in the first per-inserted user in the testUsers.sql script
//    private static final int DUMMY_USERID = 9999;
//
//    @Autowired
//    private UserDaoHibernate userDao;
//
//    @PersistenceContext
//    EntityManager em;
//
//    @Before
//    public void setUp() {
//        em.createNativeQuery("DELETE FROM users");
//        em.flush();
//    }
//
//    @Test
//    public void testCreateUser() {
//        final User user = userDao.createUser(USERNAME, PASSWORD, EMAIL, PHONE, BIRTHDATE);
//
//        assertNotNull(user);
//        assertEquals(USERNAME, user.getUsername());
//        assertEquals(PASSWORD, user.getPassword());
//        assertEquals(EMAIL, user.getEmail());
//        assertEquals(PHONE, user.getPhone());
//        assertEquals(BIRTHDATE, user.getBirthdate());
//        assertTrue(em.contains(user));
//    }
//
//    @Test
//    public void testUpdateUser() {
//        Optional<User> user = userDao.updateUser(DUMMY_USERID, PASSWORDUPDATE, EMAILUPDATE, PHONEUPDATE,
//                BIRTHDATEUPDATE);
//        assertTrue(user.isPresent());
//        assertEquals(PASSWORDUPDATE, user.get().getPassword());
//        assertEquals(EMAILUPDATE, user.get().getEmail());
//        assertEquals(PHONEUPDATE, user.get().getPhone());
//        assertEquals(BIRTHDATEUPDATE, user.get().getBirthdate());
//        assertTrue(em.contains(user.get()));
//    }
//
//    @Test
//    public void testFindUserByUserId() {
//        final Optional<User> userFound = userDao.findUserByUserId(DUMMY_USERID);
//        assertTrue(userFound.isPresent());
//        assertEquals(DUMMY_USERID, userFound.get().getUserId().intValue());
//    }
//
//    @Test
//    public void testDeleteUser() {
//        assertTrue(userDao.deleteUser(DUMMY_USERID));
//    }
//
//    @Test
//    public void testFindUserByUsername() {
//        final Optional<User> userFound = userDao.findUserByUsername(DUMMY_USERNAME);
//        assertTrue(userFound.isPresent());
//        assertEquals(DUMMY_USERNAME, userFound.get().getUsername());
//    }
//}
