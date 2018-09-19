package com.venturedive.rotikhilao.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.venturedive.rotikhilao.enums.UserType;
import com.venturedive.rotikhilao.model.Customer;
import com.venturedive.rotikhilao.model.User;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.repository.CustomerRepository;
import com.venturedive.rotikhilao.repository.RoleRepository;
import com.venturedive.rotikhilao.repository.UserRepository;
import com.venturedive.rotikhilao.request.LoginRequest;
import com.venturedive.rotikhilao.request.SignUpRequest;
import com.venturedive.rotikhilao.response.JwtAuthenticationResponse;
import com.venturedive.rotikhilao.security.JwtTokenProvider;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUserName(signUpRequest.getUsername())) {
//            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
//                    HttpStatus.BAD_REQUEST);
            BooleanResponse resp = BooleanResponse.failure("Username is already taken!");
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }

//        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
//                    HttpStatus.BAD_REQUEST);
//        }

        // Creating user's account
//        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
//                signUpRequest.getEmail(), signUpRequest.getPassword());

//        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
//                signUpRequest.getPassword());

        if (UserType.valueOf(signUpRequest.getUserType()).equals(UserType.CUSTOMER)){

            Customer customer = new Customer(signUpRequest.getUsername(), signUpRequest.getPassword(),
                    signUpRequest.getFirstName(), signUpRequest.getLastName());

            customer.setPassword(passwordEncoder.encode(customer.getPassword()));

            Customer result = customerRepository.save(customer);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/users/{username}")
                    .buildAndExpand(result.getUserName()).toUri();

            return ResponseEntity.created(location).body(BooleanResponse.success("User registered succesfully"));
        }


        return null;
    }

}
