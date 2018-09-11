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
@RequestMapping("/api/customer")
public class CustomerController {

    private ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService){
        this.customerService = customerService;
    }



    @GetMapping("/orders")
    public ResponseList<Order> viewCurrentOrders(){

        ResponseList<Order> response = customerService.viewCurrentOrders();

        return response;
    }

    @PostMapping("/orders")
    public Boolean orderFood(@RequestBody @Valid @NotNull OrderWrapper request){

        BooleanResponse response = customerService.orderFood(request);
        return null;
    }

    @GetMapping("/orders/all")
    public List<Order> viewAllOrders(){

        ResponseList<Order> response = customerService.viewAllOrders();
        return null;
    }

//    @GetMapping("/orders/expenses")
//    public ResponseList<>





}
