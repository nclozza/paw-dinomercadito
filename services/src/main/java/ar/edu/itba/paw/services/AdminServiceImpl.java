package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.AdminDAO;
import ar.edu.itba.paw.interfaces.Services.AdminService;
import ar.edu.itba.paw.models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public Admin createAdmin(Integer userAdminId) {
        return adminDAO.createAdmin(userAdminId);
    }

    @Override
    public Optional<Admin> findAdminByUserId(Integer userId) {
        return adminDAO.findAdminByUserId(userId);
    }

    @Override
    public Boolean isAdmin(Integer userId) {
        return adminDAO.isAdmin(userId);
    }
}
