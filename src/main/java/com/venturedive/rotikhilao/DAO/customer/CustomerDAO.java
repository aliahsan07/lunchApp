package com.venturedive.rotikhilao.DAO.customer;

import com.venturedive.rotikhilao.model.Customer;
import com.venturedive.rotikhilao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerDAO implements ICustomerDAO {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Customer fetchCustomerById(Long id) throws Exception {

        Customer customer = null;
        try{
            customer = customerRepository.getOne(id);
        } catch (Exception e){
            throw new Exception("Invalid UserId provided");
        }

        return customer;

    }

    @Override
    public Boolean existsById(Long id) {

        if (customerRepository.findById(id).isPresent()){
            return true;
        }

        return false;
    }

}
