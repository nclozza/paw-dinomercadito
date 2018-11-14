package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.Admin;

import java.util.Optional;

public interface AdminService {

    Admin createAdmin(Integer userAdminId);

    Optional<Admin> findAdminbyUserId(Integer userId);

    Boolean isAdmin(Integer userId);
}
