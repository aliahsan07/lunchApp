package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Boolean existsByUserName(@Param("username") String username);

    Optional<Customer> findByUserName(@Param("username") String username);

}
