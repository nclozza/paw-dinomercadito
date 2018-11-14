package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Admin;

import java.util.Optional;

public interface AdminDAO {

    Admin createAdmin(Integer userAdminId);

    Optional<Admin> findAdminbyUserId(Integer userId);

    Boolean isAdmin(Integer userId);
}
