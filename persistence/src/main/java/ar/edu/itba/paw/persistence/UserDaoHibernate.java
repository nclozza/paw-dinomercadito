package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.UserDAO;
import ar.edu.itba.paw.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoHibernate implements UserDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public User createUser(String username, String password, String email, String phone, String birthdate, Double funds) {
        final User user = new User(username, password, email, phone, birthdate);
        em.persist(user);
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

            return true;
        }

        return false;
    }

    @Transactional
    @Override
    public Optional<User> updateUser(Integer userId, String password, String email, String phone, String birthdate, Double funds) {

        User user = em.find(User.class, userId);

        if (user != null) {
            user.setPassword(password);
            user.setEmail(email);
            user.setPhone(phone);
            user.setBirthdate(birthdate);
            user.setFunds(funds);
            em.merge(user);

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

    @Transactional
    @Override
    public boolean addFundsToUserId(Double funds, Integer userId) {

        User user = em.find(User.class, userId);

        deleteUser(userId);

        if (user != null) {
            user.setFunds(funds);
            em.merge(user);

            return true;
        }

        return false;
    }
}
