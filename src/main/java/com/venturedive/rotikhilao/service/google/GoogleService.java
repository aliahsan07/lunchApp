package com.venturedive.rotikhilao.service.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.venturedive.rotikhilao.configuration.JwtTokenProvider;
import com.venturedive.rotikhilao.enums.UserType;
import com.venturedive.rotikhilao.model.Customer;
import com.venturedive.rotikhilao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class GoogleService implements IGoogleService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @Override
    public String saveNewUser(GoogleIdToken.Payload payload) {

        String userId = payload.getSubject();
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        String locale = (String) payload.get("locale");
        String familyName = (String) payload.get("family_name");
        String givenName = (String) payload.get("given_name");

        Customer customer = new Customer();
        customer.setUserName(payload.getEmail());
        customer.setName(name);
        customer.setImageUrl(pictureUrl);

        customer = customerRepository.save(customer);

        return tokenProvider.generateToken(customer.getId());

    }

    @Override
    public Boolean checkUserExistence(GoogleIdToken.Payload payload) {

        if (customerRepository.existsByUserName(payload.getEmail())){
            return true;
        }

        return false;
    }

    @Override
    public String saveNewUser(Map<String, Object> map) {

        String name = (String) map.get("name");
        String pictureUrl = (String) map.get("picture");

        Customer customer = new Customer();
        customer.setUserName((String) map.get("email"));
        customer.setName(name);
        customer.setImageUrl(pictureUrl);
        customer.setRole(UserType.CUSTOMER);

        customer = customerRepository.save(customer);

        return tokenProvider.generateToken(customer.getId());
    }

    @Override
    public String checkUserExistence(Map<String, Object> map) throws Exception {

        String domainName = (String) map.get("hd");

        if (domainName == null || !domainName.equals("venturedive.com")){
            System.out.println("Please use venturedive email address to login");
            return "UNAUTHORIZED";

        }

        Optional<Customer> customerW = customerRepository.findByUserName((String) map.get("email"));

        Customer customer = null;
        if (customerW.isPresent()){
            customer = customerW.get();

            return tokenProvider.generateToken(customer.getId());
        }

        return saveNewUser(map);

    }
}
