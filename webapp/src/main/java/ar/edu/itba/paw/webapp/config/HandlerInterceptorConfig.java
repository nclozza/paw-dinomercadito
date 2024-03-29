package ar.edu.itba.paw.webapp.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerInterceptorConfig extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
                           final ModelAndView modelAndView) {
        if (modelAndView != null) {
            modelAndView.addObject("loggedIn", isLogged());
        }
    }

    private boolean isLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !authentication.getPrincipal().equals("anonymousUser");
    }
}
