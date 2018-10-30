package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.ViewDAO;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.models.View;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ViewDaoHibernate implements ViewDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewDaoHibernate.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public View createView(Integer postId, Integer userId) {
        User user = em.find(User.class, userId);
        Post post = em.find(Post.class, postId);
        View view = new View(post, user);
        em.persist(view);
        LOGGER.info("View inserted with viewId = {}", view.getViewId().intValue());
        return view;
    }

    @Override
    public Optional<View> findViewByViewId(Integer viewId) {
        return Optional.ofNullable(em.find(View.class, viewId));
    }

    @Transactional
    @Override
    public List<View> findViewsByUserId(Integer userId) {
        User user = em.find(User.class, userId);
        Hibernate.initialize(user.getViewsList());
        return user.getViewsList();
    }

    @Transactional
    @Override
    public List<View> findViewsByPostId(Integer postId) {
        Post post = em.find(Post.class, postId);
        Hibernate.initialize(post.getViewsList());
        return post.getViewsList();
    }
}
