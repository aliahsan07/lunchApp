package com.venturedive.rotikhilao.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.venturedive.rotikhilao.enums.UserType;
import com.venturedive.rotikhilao.model.Admin;
import com.venturedive.rotikhilao.model.Customer;
import com.venturedive.rotikhilao.model.User;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.repository.AdminRepository;
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

    @Autowired
    AdminRepository adminRepository;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
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


        UserType userType = UserType.valueOf(signUpRequest.getUserType());
        URI location = null;

        switch (userType){

            case CUSTOMER:
            {
                Customer customer = new Customer(signUpRequest.getUsername(), signUpRequest.getPassword(),
                        signUpRequest.getFirstName(), signUpRequest.getLastName());

                customer.setPassword(passwordEncoder.encode(customer.getPassword()));
                customer.setRole(UserType.CUSTOMER);

                Customer result = customerRepository.save(customer);

                location = ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/api/users/{username}")
                        .buildAndExpand(result.getUserName()).toUri();

                break;
            }

            case ADMIN:
            {
                Admin admin = new Admin(signUpRequest.getUsername(), signUpRequest.getPassword(),
                        signUpRequest.getFirstName(), signUpRequest.getLastName());

                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
                admin.setRole(UserType.ADMIN);

                Admin result = adminRepository.save(admin);
                location = ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/api/users/{username}")
                        .buildAndExpand(result.getUserName()).toUri();
                break;
            }

            case OFFICE_BOY:
            {
                // TODO: assumption -> will be created by admin so this end point doesn't make sense
                break;
            }

            default:
            {
                BooleanResponse resp = BooleanResponse.failure("Invalid user type provided!");
                return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
            }


        }

        return ResponseEntity.created(location).body(BooleanResponse.success("User registered succesfully"));

    }

}
