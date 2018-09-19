package ar.edu.itba.paw.webapp.config;

import ar.edu.itba.paw.webapp.auth.PawUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@ComponentScan("ar.edu.itba.paw.webapp.auth")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PawUserDetailsService userDetailsService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService)
                .sessionManagement()
                .invalidSessionUrl("/login")
            .and().authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").authenticated()
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
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/favicon.ico", "/403");
    }
}