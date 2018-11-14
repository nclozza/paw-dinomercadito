package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.models.View;
import junit.framework.TestCase;
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

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:testViews.sql")
public class ViewDaoHibernateTest {

    private static final int POST_ID = 5555;
    private static final int USER_ID = 6666;
    private static final int VIEW_ID = 7777;

    private static final int DUMMY_POST_ID = 45678;


    @Autowired
    private ViewDaoHibernate viewDaoHibernate;

    @PersistenceContext
    EntityManager em;

    @Before
    public void setUp() {
        em.createNativeQuery("DELETE FROM views");
        em.flush();
    }

    @Test
    public void testCreateView() {
        final View view = viewDaoHibernate.createView(DUMMY_POST_ID, USER_ID);

        assertNotNull(view);
        assertEquals(DUMMY_POST_ID, view.getPostVisited().getPostId().intValue());
        assertEquals(USER_ID, view.getUserWhoVisited().getUserId().intValue());
        assertTrue(em.contains(view));
    }

    @Test
    public void testFindViewByViewId() {
        final Optional<View> view = viewDaoHibernate.findViewByViewId(VIEW_ID);

        assertTrue(view.isPresent());
        assertEquals(VIEW_ID, view.get().getViewId().intValue());
    }

    @Test
    public void testFindViewsByUserId() {
        final List<View> viewsList = viewDaoHibernate.findViewsByUserId(USER_ID);

        assertFalse(viewsList.isEmpty());

        for (View v : viewsList)
            assertEquals(USER_ID, v.getUserWhoVisited().getUserId().intValue());
    }

    @Test
    public void testFindViewsByPostId() {
        final List<View> viewsList = viewDaoHibernate.findViewsByPostId(POST_ID);

        assertFalse(viewsList.isEmpty());

        for (View v : viewsList)
            assertEquals(POST_ID, v.getPostVisited().getPostId().intValue());
    }

    @Test
    public void testCheckIfUserVisitedPost() {
        final List<View> viewsList = viewDaoHibernate.checkIfUserVisitedPost(POST_ID, USER_ID);

        assertFalse(viewsList.isEmpty());
        assertEquals(1, viewsList.size());
    }

    @Test
    public void testFindLastViewsByUserId() {
        final List<View> viewsList = viewDaoHibernate.findLastViewsByUserId(USER_ID);

        assertFalse(viewsList.isEmpty());

        for (View v : viewsList)
            assertEquals(USER_ID, v.getUserWhoVisited().getUserId().intValue());
    }
}
