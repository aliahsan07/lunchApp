package com.venturedive.rotikhilao.DAO.order;

import com.venturedive.rotikhilao.model.Order;

import java.util.List;

public interface IOrderDAO {

    public abstract List<Order> fetchCurrentOrders(Long orderedBy);

    public abstract List<Order> fetchAllUserOrders(Long orderedBy);

    public abstract void saveOrder(Order order);

    public abstract void cancelOrder(Order order);

    public abstract Order fetchOrderById(Long id) throws Exception;
}
