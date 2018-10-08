package com.venturedive.rotikhilao.DAO.order;

import com.venturedive.rotikhilao.enums.OrderStatus;
import com.venturedive.rotikhilao.model.Order;
import com.venturedive.rotikhilao.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAO implements IOrderDAO {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> fetchCurrentOrders(Long orderedBy) {

        List<Order> orders = orderRepository.findAllByOrderedByIdAndOrderStatusOrderByOrderTimeDesc(orderedBy, OrderStatus.PREPARING.value());

        return orders;
    }

    @Override
    public List<Order> fetchAllUserOrders(Long orderedBy) {

        List<Order> orders = orderRepository.findAllByOrderedByIdOrderByOrderTimeDesc(orderedBy);

        return orders;
    }

    @Override
    public void saveOrder(Order order) {

        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Order order) {

        orderRepository.delete(order);
    }

    @Override
    public Order fetchOrderById(Long id) throws Exception {

        Order order = null;
        try{
            order = orderRepository.getOne(id);
        } catch (Exception e){
            throw new Exception("Invalid OrderId  provided");
        }

        return order;
    }


}
