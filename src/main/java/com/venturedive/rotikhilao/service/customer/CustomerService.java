package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.DTO.FoodItemDTO;
import com.venturedive.rotikhilao.enums.OrderStatus;
import com.venturedive.rotikhilao.model.OfficeBoy;
import com.venturedive.rotikhilao.model.Order;
import com.venturedive.rotikhilao.model.OrderItem;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.repository.OfficeBoyRepository;
import com.venturedive.rotikhilao.repository.OrderRepository;
import com.venturedive.rotikhilao.request.OrderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    //TODO: add validation checks

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OfficeBoyRepository officeBoyRepository;


    @Override
    public ResponseList<Order> viewCurrentOrders() {

        // fetch user Id from token and then fetch their orders
        List<Order> orders = orderRepository.findAllByOrderedByAndOrderStatus(1L, 1);

        ResponseList<Order> responseList = new ResponseList<>();
        responseList.setData(orders);

        return responseList;
    }

    @Override
    public ResponseList<Order> viewAllOrders() {

        List<Order> orders = orderRepository.findAllByOrderedBy(1L);

        ResponseList<Order> responseList = new ResponseList<>();
        responseList.setData(orders);

        return responseList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public BooleanResponse orderFood(OrderWrapper request) {

        List<FoodItemDTO> foodList = request.getOrderList();

        Order order = new Order();
        order.setOrderStatus(OrderStatus.PREPARING);

        for (FoodItemDTO item : foodList){

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(item.getQuantity());

        }
        order.setTotalPrice(request.getTotalPrice());
        order.setAssignedTo(fetchWorker());
        order.setOrderTime(LocalDateTime.now() );


        orderRepository.save(order);
        return BooleanResponse.success();
    }

    private OfficeBoy fetchWorker(){

        OfficeBoy officeBoy = officeBoyRepository.getOne(18L);

        return officeBoy;
    }
}
