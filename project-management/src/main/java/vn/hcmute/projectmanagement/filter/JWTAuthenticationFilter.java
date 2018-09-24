package vn.hcmute.projectmanagement.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import vn.hcmute.projectmanagement.service.impl.TokenAuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("JWTAuthenticationFilter.doFilter");

        Authentication authentication = TokenAuthenticationService
                .getAuthentication((HttpServletRequest) servletRequest);
//        System.out.println("granted authorities : "+authentication.getAuthorities());


        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("filterChain : "+filterChain);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
