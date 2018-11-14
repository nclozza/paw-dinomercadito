package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.DAO.UserNotAuthenticatedDAO;
import ar.edu.itba.paw.models.UserNotAuthenticated;
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
public class UserNotAuthenticatedDaoHibernate implements UserNotAuthenticatedDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserNotAuthenticatedDaoHibernate.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public UserNotAuthenticated createUser(String username, String password, String email, String phone,
                                           String birthdate, String signUpDate, Integer code) {
        UserNotAuthenticated user = new UserNotAuthenticated(username, password, email, phone, birthdate, signUpDate, code);
        em.persist(user);
        LOGGER.info("User inserted with userId = {}", user.getUserId().intValue());
        return user;
    }

    @Override
    public Optional<UserNotAuthenticated> findUserByUserId(Integer userId) {
        return Optional.ofNullable(em.find(UserNotAuthenticated.class, userId));
    }

    @Override
    public Optional<UserNotAuthenticated> findUserByUsername(String username) {
        final TypedQuery<UserNotAuthenticated> query = em.createQuery("from UserNotAuthenticated as u where u.username = :username", UserNotAuthenticated.class);
        query.setParameter("username", username);
        final List<UserNotAuthenticated> list = query.getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(0));
    }

    @Override
    public Optional<UserNotAuthenticated> findUserByCode(Integer code) {
        final TypedQuery<UserNotAuthenticated> query = em.createQuery("from UserNotAuthenticated as u where u.code = :code", UserNotAuthenticated.class);
        query.setParameter("code", code);
        final List<UserNotAuthenticated> list = query.getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(0));
    }

    @Override
    public Optional<UserNotAuthenticated> findUserByEmail(String email) {
        final TypedQuery<UserNotAuthenticated> query = em.createQuery("from UserNotAuthenticated as u where u.email = :email", UserNotAuthenticated.class);
        query.setParameter("email", email);
        final List<UserNotAuthenticated> list = query.getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(0));
    }

    @Transactional
    @Override
    public boolean deleteUser(Integer userId) {
        UserNotAuthenticated user = em.find(UserNotAuthenticated.class, userId);

        if (user != null) {
            em.remove(user);
            LOGGER.info("User deleted with userId = {}", userId);

            return true;
        }
        LOGGER.info("User not found with userId = {}", userId);

        return false;
    }

    @Override
    public boolean checkCodeDoesNotExist(Integer code) {
        final TypedQuery<UserNotAuthenticated> query = em.createQuery("from UserNotAuthenticated as u where u.code = :code", UserNotAuthenticated.class);
        query.setParameter("code", code);
        final List<UserNotAuthenticated> list = query.getResultList();

        return list.isEmpty();
    }

    @Override
    public boolean checkUsername(String username) {
        final TypedQuery<UserNotAuthenticated> query = em.createQuery("from UserNotAuthenticated as u where u.username = :username", UserNotAuthenticated.class);
        query.setParameter("username", username);
        final List<UserNotAuthenticated> list = query.getResultList();

        return list.isEmpty();
    }

    @Override
    public boolean checkEmail(String email) {
        final TypedQuery<UserNotAuthenticated> query = em.createQuery("from UserNotAuthenticated as u where u.email = :email", UserNotAuthenticated.class);
        query.setParameter("email", email);
        final List<UserNotAuthenticated> list = query.getResultList();

        return list.isEmpty();
    }
}
