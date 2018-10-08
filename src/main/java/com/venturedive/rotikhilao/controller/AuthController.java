package com.venturedive.rotikhilao.controller;

//import com.venturedive.rotikhilao.filters.OpenIdConnectFilter;
import com.venturedive.rotikhilao.configuration.GoogleTokenVerifier;
import com.venturedive.rotikhilao.pojo.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api")
@Slf4j
public class AuthController {

    @Autowired
    public OAuth2RestOperations restTemplate;

    @Autowired
    GoogleTokenVerifier googleTokenVerifier;


    @PostMapping(value="/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public @ResponseBody
    TokenResponse authenticateUser(@RequestBody String body) throws Exception {

        log.info(body);
        String token =  googleTokenVerifier.verifyIdToken(body);

        return new TokenResponse(token);

    }

}
