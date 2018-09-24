package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.DAO.FoodItem.FoodItemDAO;
import com.venturedive.rotikhilao.DAO.FoodItem.IFoodItemDAO;
import com.venturedive.rotikhilao.DAO.customer.CustomerDAO;
import com.venturedive.rotikhilao.DAO.customer.ICustomerDAO;
import com.venturedive.rotikhilao.DAO.officeBoy.OfficeBoyDAO;
import com.venturedive.rotikhilao.DAO.order.IOrderDAO;
import com.venturedive.rotikhilao.DAO.order.OrderDAO;
import com.venturedive.rotikhilao.DTO.FoodItemDTO;
import com.venturedive.rotikhilao.enums.OrderStatus;
import com.venturedive.rotikhilao.mapper.MenuMapper;
import com.venturedive.rotikhilao.model.Customer;
import com.venturedive.rotikhilao.model.FoodItem;
import com.venturedive.rotikhilao.model.OfficeBoy;
import com.venturedive.rotikhilao.model.Order;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.MenuResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.request.OrderWrapper;
import com.venturedive.rotikhilao.service.util.ServiceUtil;
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
    private ServiceUtil serviceUtil;


    @Override
    public ResponseList<Order> viewCurrentOrders(Long customerId) throws Exception {

        // fetch user Id from token and then fetch their orders
        new CustomerDAO().fetchCustomerById(customerId);

        List<Order> orders = new OrderDAO().fetchCurrentOrders(customerId);

        ResponseList<Order> responseList = new ResponseList<>();
        responseList.setData(orders);

        return responseList;
    }


    @Override
    public ResponseList<Order> viewAllOrders(Long customerId) throws Exception {

        new CustomerDAO().fetchCustomerById(customerId);

        List<Order> orders = new OrderDAO().fetchAllUserOrders(customerId);

        ResponseList<Order> responseList = new ResponseList<>();
        responseList.setData(orders);

        return responseList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public BooleanResponse orderFood(OrderWrapper request) throws Exception {

        ICustomerDAO customerDAO = new CustomerDAO();

        if (request.getCustomerId() == null || (!customerDAO.existsById(request.getCustomerId()))){
            throw new Exception("Sorry! Invalid customerId provided");
        }

        Customer customer = new CustomerDAO().fetchCustomerById(request.getCustomerId());


        List<FoodItemDTO> foodList = request.getOrderList() == null ? null : request.getOrderList();

        if (foodList ==  null || foodList.isEmpty()){
            throw new Exception("Sorry! Your cart is empty");
        }

        Order order = new Order();
        IFoodItemDAO foodItemDAO = new FoodItemDAO();
        IOrderDAO orderDAO = new OrderDAO();
        order.setOrderStatus(OrderStatus.PREPARING.value());

        if (request.getTotalPrice() != null){
            order.setTotalPrice(request.getTotalPrice());
        }

        order.setAssignedTo(fetchWorker());
        order.setOrderTime(LocalDateTime.now() );
        order.setOrderedBy(customer);

        for (FoodItemDTO item : foodList){

            FoodItem foodItem = foodItemDAO.fetchFoodItemById(item.getItemId());
            order.addFoodItem(foodItem, item.getQuantity());
        }
        orderDAO.saveOrder(order);

        return BooleanResponse.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public BooleanResponse cancelOrder(Long customerId, Long orderId) throws Exception {

        new CustomerDAO().fetchCustomerById(customerId);

        Order order = validateOrderFromCustomer(customerId, orderId);

        new OrderDAO().cancelOrder(order);

        return BooleanResponse.success();


    }

    private Order validateOrderFromCustomer(Long customerId, Long orderId) throws Exception {

        Order order = new OrderDAO().fetchOrderById(orderId);
        if (!order.getOrderedById().equals(customerId)){
            throw new Exception("You're not authorized to access this order");
        }
        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public BooleanResponse updateOrder(Long customerId, Long orderId, OrderWrapper request) throws Exception {

        Customer customer = new CustomerDAO().fetchCustomerById(customerId);

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

        List<FoodItem> foodItems = new FoodItemDAO().findAllItemsInPriceRange(fromPrice, toPrice);

        MenuResponse menuResponse = new MenuResponse();

        List<FoodItemDTO> wrappedFoodItems = MenuMapper.wrapFoodItems(foodItems);

        menuResponse.setItems(wrappedFoodItems);

        return menuResponse;

    }

    private OfficeBoy fetchWorker() throws Exception {

        // TODO: this lies under admin portal probably
        OfficeBoy officeBoy = new OfficeBoyDAO().fetchOfficeBoyById(3L);

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
