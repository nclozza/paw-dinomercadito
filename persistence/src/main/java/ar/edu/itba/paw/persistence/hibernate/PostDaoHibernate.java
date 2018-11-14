package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.DAO.PostDAO;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class PostDaoHibernate implements PostDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostDaoHibernate.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Post createPost(Integer productId, Double price, Integer userId, String description, Integer productQuantity,Integer visits) {
        User user = em.find(User.class, userId);
        Product product = em.find(Product.class, productId);
        Post post = new Post(product, price, user, description, productQuantity, visits);
        em.persist(post);
        LOGGER.info("Post inserted with postId = {}", post.getPostId().intValue());
        return post;
    }

    @Transactional
    @Override
    public boolean deletePost(Integer postId) {
        Post post = em.find(Post.class, postId);

        if (post != null){
            em.remove(post);
            LOGGER.info("Post deleted with postId = {}", postId);

            return true;
        }
        LOGGER.info("Post not found with postId = {}", postId);

        return false;
    }

    @Transactional
    @Override
    public Optional<Post> updatePost(Integer postId, Integer productId, Double price, String description, Integer productQuantity, Integer visits) {
        Post post = em.find(Post.class, postId);
        Product product = em.find(Product.class, productId);

        if (post != null){
            post.setProductPosted(product);
            post.setPrice(price);
            post.setDescription(description);
            post.setProductQuantity(productQuantity);
            post.setVisits(visits);
            em.merge(post);
            LOGGER.info("Post updated with postId = {}", postId);
        } else {
            LOGGER.info("Post not found with postId = {}", postId);
        }

        return Optional.ofNullable(post);
    }

    @Override
    public Optional<Post> findPostByPostId(Integer postId) {
        return Optional.ofNullable(em.find(Post.class, postId));
    }

    @Transactional
    @Override
    public List<Post> findPostsByUserId(Integer userId) {
        User user = em.find(User.class, userId);
        Hibernate.initialize(user.getPostList());
        return user.getPostList();
    }

    @Override
    public List<Post> findPostsByProductId(Integer productId) {
        final TypedQuery<Post> query = em.createQuery("FROM Post p " +
                "WHERE p.productPosted.productId = :productId " +
                "AND p.disable = false", Post.class);
        query.setParameter("productId", productId);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Optional<Post> addVisit(final Integer postId){
        Post post = em.find(Post.class, postId);

        if (post != null){
            post.setVisits(post.getVisits() + 1);
            em.merge(post);
            LOGGER.info("Post add visit with postId = {}", postId);
        } else {
            LOGGER.info("Post not found with postId = {}", postId);
        }

        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> findMostVisitedPosts() {
        final TypedQuery<Post> query = em.createQuery("from Post p " +
                "where p.disable = false " +
                "order by visits desc", Post.class);
        query.setMaxResults(50);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<Post> updatePostProductQuantity(Integer postId ,Integer productQuantity) {
        Post post = em.find(Post.class, postId);

        if (post != null){
            post.setProductQuantity(productQuantity);
            em.merge(post);
            LOGGER.info("Post updated with postId = {}", postId);
        } else {
            LOGGER.info("Post not found with postId = {}", postId);
        }

        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> findPostsByFilter(String filter) {
        final String filterFormatted = "%" + filter.toLowerCase() + "%";

        final TypedQuery<Post> query = em.createQuery("SELECT DISTINCT p FROM Post p " +
                "WHERE LOWER(p.description) LIKE :filter " +
                "OR LOWER(p.productPosted.productName) LIKE :filter " +
                "OR LOWER(p.userSeller.username) LIKE :filter", Post.class);
        query.setParameter("filter", filterFormatted);
        final List<Post> list = query.getResultList();

        return list;
    }

    @Transactional
    @Override
    public void disablePost(Post post){
        post.setDisable(true);
        em.merge(post);
        LOGGER.info("Post disable with postId = {}", post.getPostId());
    }

    @Transactional
    @Override
    public void enablePost(Post post){
        post.setDisable(false);
        em.merge(post);
        LOGGER.info("Post enable with postId = {}", post.getPostId());
    }

    @Override
    public List<Post> findAllPosts() {
        final TypedQuery<Post> query = em.createQuery("from Post", Post.class);
        final List<Post> list = query.getResultList();

        return list;
    }
}
