package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.PostDAO;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.Product;
import ar.edu.itba.paw.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PostDaoHibernate implements PostDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostDaoHibernate.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Post createPost(Integer productId, Double price, Integer userId, String description, Integer productQuantity) {
        User user = em.find(User.class, userId);
        Product product = em.find(Product.class, productId);
        Post post = new Post(product, price, user, description, productQuantity);
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
    public Optional<Post> updatePost(Integer postId, Integer productId, Double price, String description, Integer productQuantity) {
        Post post = em.find(Post.class, postId);
        Product product = em.find(Product.class, productId);

        if (post != null){
            post.setProduct(product);
            post.setProductId(productId);
            post.setPrice(price);
            post.setDescription(description);
            post.setProductQuantity(productQuantity);
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

    @Override
    public List<Post> findPostsByUserId(Integer userId) {
        User user = em.find(User.class, userId);
        return user.getPostList();
    }

    @Override
    public List<Post> findPostsByProductId(Integer productId) {
        Product product = em.find(Product.class, productId);
        return product.getPostList();
    }
}
