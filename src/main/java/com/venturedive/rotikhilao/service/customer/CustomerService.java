package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.DTO.FoodItemDTO;
import com.venturedive.rotikhilao.enums.OrderStatus;
import com.venturedive.rotikhilao.model.*;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.repository.*;
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

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;


    @Override
    public ResponseList<Order> viewCurrentOrders(Long customerId) throws Exception {

        // fetch user Id from token and then fetch their orders
        Customer customer = null;
        try{
            customer = customerRepository.getOne(customerId);
        } catch (Exception e){
            throw new Exception("Invalid UserId provided");
        }

        List<Order> orders = orderRepository.findAllByOrderedByIdAndOrderStatus(customerId, OrderStatus.PREPARING.value());

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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public BooleanResponse orderFood(OrderWrapper request) throws Exception {

        if (request.getCustomerId() == null || (!customerRepository.findById(request.getCustomerId()).isPresent())){
            throw new Exception("Sorry! Invalid customerId provided");
        }

        Customer customer = customerRepository.getOne(request.getCustomerId());


        List<FoodItemDTO> foodList = request.getOrderList() == null ? null : request.getOrderList();

        if (foodList ==  null || foodList.isEmpty()){
            throw new Exception("Sorry! Your cart is empty");
        }

        Order order = new Order();
        order.setOrderStatus(OrderStatus.PREPARING.value());

        if (request.getTotalPrice() != null){
            order.setTotalPrice(request.getTotalPrice());
        }

        order.setAssignedTo(fetchWorker());
        order.setOrderTime(LocalDateTime.now() );
        order.setOrderedBy(customer);

        for (FoodItemDTO item : foodList){

            FoodItem foodItem = foodItemRepository.getOne(item.getItemId());
            order.addFoodItem(foodItem, item.getQuantity());
        }
        orderRepository.save(order);

        return BooleanResponse.success();
    }

    private OfficeBoy fetchWorker(){

        OfficeBoy officeBoy = officeBoyRepository.getOne(3L);

        return officeBoy;
    }
}
