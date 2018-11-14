package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.interfaces.Services.AdminService;
import ar.edu.itba.paw.interfaces.Services.UserService;
import ar.edu.itba.paw.models.Admin;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class PawUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService us;

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<User> user = us.findUserByUsername(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("No user by the name " + username);
        }

        final Collection<? extends GrantedAuthority> authorities;

        Optional<Admin> admin = adminService.findAdminByUserId(user.get().getUserId());

        if (admin.isPresent()) {
            authorities = Arrays.asList(
                    new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new org.springframework.security.core.userdetails.User(username, user.get().getPassword(), authorities);
    }
}