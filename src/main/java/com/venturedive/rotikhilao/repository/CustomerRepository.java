package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseUserRepository<Customer> {

}
