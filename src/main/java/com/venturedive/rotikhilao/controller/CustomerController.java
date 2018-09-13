package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.model.Order;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.MenuResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.request.OrderWrapper;
import com.venturedive.rotikhilao.service.customer.ICustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@RequestMapping("/api/customers")
public class CustomerController {

    private ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/orders/{customerId}/current")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get patient's current orders")
    public ResponseList<Order> viewCurrentOrders(@PathVariable(name = "customerId") Long customerId) throws Exception {

        return customerService.viewCurrentOrders(customerId);

    }

    @GetMapping("/orders/{customerId}/all")
    public ResponseList<Order> viewAllOrders(@PathVariable(name = "customerId") Long customerId) throws Exception {

        return customerService.viewAllOrders(customerId);
    }

    @PostMapping("/orders")
    public BooleanResponse orderFood(@RequestBody @Valid @NotNull OrderWrapper request) throws Exception {

        log.info("ORDER FOOD REQUEST RECEIVED");

        return customerService.orderFood(request);
    }


    @DeleteMapping("/orders/{customerId}/{orderId}")
    public BooleanResponse cancelOrder(@PathVariable(name="customerId") Long customerId,
                                       @PathVariable(name="orderId") Long orderId) throws Exception {

        return customerService.cancelOrder(customerId, orderId);

    }


    @PutMapping("/orders/{customerId}/{orderId}")
    public BooleanResponse updateOrder(@PathVariable(name="customerId") Long customerId,
                                       @PathVariable(name="orderId") Long orderId,
                                       @RequestBody @Valid @NotNull OrderWrapper request) throws Exception {

        return customerService.updateOrder(customerId,orderId, request);
    }


    // TODO:
    // 1. View menu
    // 2. Filter menu items
    // 3.

    @GetMapping(value = "/vendors/{vendorId}/menu")
    public MenuResponse displayMenu(@PathVariable(value="vendorId") Long vendorId ) throws Exception {

        log.info("DISPLAY MENU REQUEST RECEIVED");
        return customerService.displayMenu(vendorId);

    }


    @GetMapping(value ="/vendors/menu")
    public MenuResponse filterMenuByPrice(@RequestParam(value = "fromPrice") Integer fromPrice,
                                          @RequestParam(value="toPrice") Integer toPrice) {

        //return customerService.filterMenuByPrice(fromPrice, toPrice);
        return null;
    }


}
