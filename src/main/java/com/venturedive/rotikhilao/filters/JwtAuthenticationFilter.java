package com.venturedive.rotikhilao.filters;

import com.venturedive.rotikhilao.configuration.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;


    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().equals("/api/login") || request.getRequestURI().equals("/api/customers/dailymenu")){
            filterChain.doFilter(request, response);
        }

        else {

            try {
                String jwt = getJwtFromRequest(request);

                if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                    logger.info("User signed in: " + tokenProvider.getUserIdFromJWT(jwt));
                } else {
                    throw new ServletException("unable to authenticate user");
                }

            } catch (Exception ex) {
                logger.error("Could not set user authentication in security context", ex);
            }

            filterChain.doFilter(request, response);

        }

    }


    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) ) {
            return bearerToken;
        }
        return null;
    }

}
