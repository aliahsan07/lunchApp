package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.model.Order;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.request.OrderWrapper;
import com.venturedive.rotikhilao.service.customer.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/customers")
public class CustomerController {

    private ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }


    // TODO:
    // 1. Add new order
    // 2. View order
    // 3. Update order
    // 4. Cancel order
    // 5. View all previous orders


    @GetMapping("/orders/{customerId}/current")
    public ResponseList<Order> viewCurrentOrders(@PathVariable(name = "customerId") Long customerId) throws Exception {

        ResponseList<Order> response = customerService.viewCurrentOrders(customerId);

        return response;
    }

    @PostMapping("/orders")
    public BooleanResponse orderFood(@RequestBody @Valid @NotNull OrderWrapper request) throws Exception {

        log.info("ORDER FOOD REQUEST RECEIVED");

        return customerService.orderFood(request);
    }

    @GetMapping("/orders/{customerId}/all")
    public List<Order> viewAllOrders() {

        ResponseList<Order> response = customerService.viewAllOrders();
        return null;
    }

//    @GetMapping("/orders/expenses")
//    public ResponseList<>


}
