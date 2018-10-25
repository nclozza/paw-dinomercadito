package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.UserDAO;
import ar.edu.itba.paw.models.User;
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
public class UserDaoHibernate implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoHibernate.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public User createUser(String username, String password, String email, String phone, String birthdate) {
        User user = new User(username, password, email, phone, birthdate);
        em.persist(user);
        LOGGER.info("User inserted with userId = {}", user.getUserId().intValue());
        return user;
    }

    @Override
    public Optional<User> findUserByUserId(Integer userId) {
        return Optional.ofNullable(em.find(User.class, userId));
    }

    @Transactional
    @Override
    public boolean deleteUser(Integer userId) {
        User user = em.find(User.class, userId);

        if (user != null) {
            em.remove(user);
            LOGGER.info("User deleted with userId = {}", userId);

            return true;
        }
        LOGGER.info("User not found with userId = {}", userId);

        return false;
    }

    @Transactional
    @Override
    public Optional<User> updateUser(Integer userId, String password, String email, String phone, String birthdate) {

        User user = em.find(User.class, userId);

        if (user != null) {
            user.setPassword(password);
            user.setEmail(email);
            user.setPhone(phone);
            user.setBirthdate(birthdate);
            em.merge(user);
            LOGGER.info("User updated with userId = {}", userId);
        }else {
            LOGGER.info("User not found with userId = {}", userId);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        final TypedQuery<User> query = em.createQuery("from User as u where u.username = :username", User.class);
        query.setParameter("username", username);
        final List<User> list = query.getResultList();
        return list.isEmpty() ? null : Optional.ofNullable(list.get(0));
    }

    @Override
    public boolean checkUsername(String username) {
        final TypedQuery<User> query = em.createQuery("from User as u where u.username = :username", User.class);
        query.setParameter("username", username);
        final List<User> list = query.getResultList();

        return list.isEmpty();

    }

//    @Transactional
//    @Override
//    public boolean addFundsToUserId(Double funds, Integer userId) {
//
//        User user = em.find(User.class, userId);
//
//        if (user != null) {
//            user.setFunds(funds);
//            em.merge(user);
//            LOGGER.info("Add funds = {} to userId = {}", funds, userId);
//            return true;
//        }
//        LOGGER.info("User not found with userId = {}", userId);
//
//        return false;
//    }
}
