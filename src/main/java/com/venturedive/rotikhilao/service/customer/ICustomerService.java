package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.model.Order;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.request.OrderWrapper;

public interface ICustomerService {
    
    ResponseList<Order> viewCurrentOrders(Long customerId) throws Exception;

    ResponseList<Order> viewAllOrders();

    BooleanResponse orderFood(OrderWrapper request) throws Exception;
}
