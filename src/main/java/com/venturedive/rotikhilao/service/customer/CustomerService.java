package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.DTO.FoodItemDTO;
import com.venturedive.rotikhilao.enums.FoodItemStatus;
import com.venturedive.rotikhilao.enums.OrderStatus;
import com.venturedive.rotikhilao.mapper.MenuMapper;
import com.venturedive.rotikhilao.model.*;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.MenuResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.repository.*;
import com.venturedive.rotikhilao.request.OrderWrapper;
import com.venturedive.rotikhilao.service.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
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
    private FoodItemRepository foodItemRepository;

    @Autowired
    private ServiceUtil serviceUtil;


    @Override
    public ResponseList<Order> viewCurrentOrders(Long customerId) throws Exception {

        // fetch user Id from token and then fetch their orders
        validateCustomer(customerId);

        List<Order> orders = orderRepository.findAllByOrderedByIdAndOrderStatusOrderByOrderTimeDesc(customerId, OrderStatus.PREPARING.value());

        ResponseList<Order> responseList = new ResponseList<>();
        responseList.setData(orders);

        return responseList;
    }

    private Customer validateCustomer(Long customerId) throws Exception {
        Customer customer = null;
        try{
            customer = customerRepository.getOne(customerId);
        } catch (Exception e){
            throw new Exception("Invalid UserId provided");
        }

        return customer;
    }

    @Override
    public ResponseList<Order> viewAllOrders(Long customerId) throws Exception {

        validateCustomer(customerId);

        List<Order> orders = orderRepository.findAllByOrderedByIdOrderByOrderTimeDesc(customerId);

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

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public BooleanResponse cancelOrder(Long customerId, Long orderId) throws Exception {

        validateCustomer(customerId);

        Order order = validateOrderFromCustomer(customerId, orderId);

        orderRepository.delete(order);

        return BooleanResponse.success();


    }

    private Order validateOrderFromCustomer(Long customerId, Long orderId) throws Exception {
        if (!orderRepository.findById(orderId).isPresent()){
            throw new Exception("Invalid orderId provided.");
        }

        Order order = orderRepository.getOne(orderId);
        if (!order.getOrderedById().equals(customerId)){
            throw new Exception("You're not authorized to access this order");
        }
        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public BooleanResponse updateOrder(Long customerId, Long orderId, OrderWrapper request) throws Exception {

        Customer customer = validateCustomer(customerId);

        Order order = validateOrderFromCustomer(customerId, orderId);

        List<FoodItemDTO> foodList = request.getOrderList() == null ? null : request.getOrderList();

        if (foodList ==  null || foodList.isEmpty()){
            throw new Exception("Sorry! There is nothing to be updated");
        }

        return null;


    }

    @Override
    public MenuResponse displayMenu(Long vendorId) throws Exception {

        return serviceUtil.displayMenu(vendorId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public MenuResponse filterMenuByPrice(Integer fromPrice, Integer toPrice) throws Exception {

        validatePriceRange(fromPrice, toPrice);

        List<FoodItem> foodItems = foodItemRepository.findAllByPriceBetweenAndStatus(fromPrice, toPrice, FoodItemStatus.ACTIVE.value());

        MenuResponse menuResponse = new MenuResponse();

        List<FoodItemDTO> wrappedFoodItems = MenuMapper.wrapFoodItems(foodItems);

        menuResponse.setItems(wrappedFoodItems);

        return menuResponse;

    }

    private OfficeBoy fetchWorker(){

        // TODO: this lies under admin portal probably
        OfficeBoy officeBoy = officeBoyRepository.getOne(3L);

        return officeBoy;
    }

    private void validatePriceRange(Integer from, Integer to) throws Exception {

        if (from.intValue() < 0 || to.intValue() <0){
            throw new Exception("Invalid range provided. Try again");
        }

        if (to.intValue() < from.intValue()){
            throw new Exception("Invalid range provided. toPrice should be >= fromPrice");
        }
    }
}
