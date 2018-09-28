package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.filters.OpenIdConnectFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    OpenIdConnectFilter openIdConnectFilter;

    @RequestMapping("/")
    @ResponseBody
    public final String home(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        openIdConnectFilter.attemptAuthentication(request, response);
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(username);
        return "Welcome, " + username;
    }

}