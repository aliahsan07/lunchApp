package com.venturedive.rotikhilao.DAO.customer;

import com.venturedive.rotikhilao.model.Customer;

public interface ICustomerDAO {

    public abstract Customer fetchCustomerById(Long id) throws Exception;

    public abstract Boolean existsById(Long id);
}
