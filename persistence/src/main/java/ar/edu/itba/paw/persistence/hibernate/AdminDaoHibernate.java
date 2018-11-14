package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.DAO.AdminDAO;
import ar.edu.itba.paw.models.Admin;
import ar.edu.itba.paw.models.User;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class AdminDaoHibernate implements AdminDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDaoHibernate.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Admin createAdmin(Integer userAdminId) {
        User user = em.find(User.class, userAdminId);
        Admin admin = new Admin(user);
        em.persist(admin);
        LOGGER.info("Admin inserted with adminId = {}", admin.getAdminId().intValue());
        return admin;
    }

    @Transactional
    @Override
    public Optional<Admin> findAdminbyUserId(Integer userId){
        User user = em.find(User.class, userId);
        Hibernate.initialize(user.getAdmin());
        return Optional.ofNullable(user.getAdmin());
    }

    @Transactional
    @Override
    public Boolean isAdmin(Integer userId){
        User user = em.find(User.class, userId);
        Hibernate.initialize(user.getAdmin());
        return user.getAdmin() != null;
    }
}
