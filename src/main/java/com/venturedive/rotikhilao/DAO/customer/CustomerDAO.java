package com.venturedive.rotikhilao.DAO.customer;

import com.venturedive.rotikhilao.model.Customer;
import com.venturedive.rotikhilao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class CustomerDAO implements ICustomerDAO {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Customer fetchCustomerById(Long id) throws Exception {

        Optional<Customer> customerExists = customerRepository.findById(id);
        if (customerExists.isPresent()){
            return customerExists.get();
        }
        throw new Exception("Invalid UserId provided");

    }

    @Override
    public Boolean existsById(Long id) {

        if (customerRepository.findById(id).isPresent()){
            return true;
        }

        return false;
    }

}
