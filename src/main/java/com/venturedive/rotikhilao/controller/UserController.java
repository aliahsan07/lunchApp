package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.response.UserSummary;
import com.venturedive.rotikhilao.security.CurrentUser;
import com.venturedive.rotikhilao.security.UserPrincipal;
import com.venturedive.rotikhilao.service.user.IUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value="/profile/me")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Fetch user profile")
    @PreAuthorize("hasRole('CUSTOMER')")
    public UserSummary viewProfile(@CurrentUser UserPrincipal currentUser){

        return userService.viewProfile(currentUser);
    }
}
