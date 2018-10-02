package ar.edu.itba.paw.webapp.config;

import ar.edu.itba.paw.webapp.auth.PawUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@ComponentScan("ar.edu.itba.paw.webapp.auth")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAuthConfig.class);


    @Autowired
    private PawUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService)
                .sessionManagement()
                .invalidSessionUrl("/index")
            .and().authorizeRequests()
                .antMatchers("/index", "/products", "/posts", "/post").permitAll()
                .antMatchers("/login", "/signUp", "/authentication").anonymous()
                .antMatchers("/profile", "/logout", "/post", "/newPost", "/editPost", "/sellerInformation",
                        "/profile/addFunds").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").denyAll()
            .and().formLogin()
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .defaultSuccessUrl("/index", false)
                .loginPage("/login")
            .and().rememberMe()
                .rememberMeParameter("j_rememberme")
                .userDetailsService(userDetailsService)
                .key("mysupersecretketthatnobodyknowsabout")
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30))
            .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
            .and().exceptionHandling()
                .accessDeniedPage("/403")
            .and().csrf().disable();
    }

    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/favicon.ico", "/403");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
