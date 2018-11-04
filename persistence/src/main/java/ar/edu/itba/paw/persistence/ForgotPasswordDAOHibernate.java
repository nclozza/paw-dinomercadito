package ar.edu.itba.paw.persistence;


import ar.edu.itba.paw.interfaces.DAO.ForgotPasswordDAO;
import ar.edu.itba.paw.models.ForgotPassword;
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
public class ForgotPasswordDAOHibernate implements ForgotPasswordDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForgotPasswordDAOHibernate.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public ForgotPassword createNewRequest(Integer userId, String requestDate, String code){
        User user = em.find(User.class, userId);
        ForgotPassword forgotPassword = new ForgotPassword(user, requestDate, code);
        em.persist(forgotPassword);
        LOGGER.info("New forgot password request inserted with forgotPasswordId = {}", forgotPassword.getForgotPasswordId().intValue());
        return forgotPassword;
    }

    @Override
    public Optional<User> findUserByCode(String code){
        final TypedQuery<User> query = em.createQuery("SELECT f.userForgot FROM ForgotPassword f " +
                "WHERE f.code = :code", User.class);

        query.setParameter("code", code);
        final List<User> list = query.getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(0));
    }

    @Override
    public Boolean checkCode(String code){
        final TypedQuery<ForgotPassword> query = em.createQuery("FROM ForgotPassword f " +
                "WHERE f.code = :code", ForgotPassword.class);

        query.setParameter("code", code);
        final List<ForgotPassword> list = query.getResultList();
        return list.isEmpty()? true : false;
    }


    @Transactional
    @Override
    public void deleteRequestById(Integer forgetPasswordId){
        ForgotPassword forgetPassword = em.find(ForgotPassword.class, forgetPasswordId);

        if(forgetPassword != null){
            em.remove(forgetPassword);
            LOGGER.info("Forgot password request deleted with ForgotPasswordId = {}", forgetPasswordId);
        } else {
            LOGGER.info("Forgot password request not found with ForgotPasswordId = {}", forgetPasswordId);
        }
    }

    @Override
    public Optional<ForgotPassword> findRequestByCode(String code){
        final TypedQuery<ForgotPassword> query = em.createQuery("FROM ForgotPassword f " +
                "WHERE f.code = :code", ForgotPassword.class);

        query.setParameter("code", code);
        final List<ForgotPassword> list = query.getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(0));
    }
}
