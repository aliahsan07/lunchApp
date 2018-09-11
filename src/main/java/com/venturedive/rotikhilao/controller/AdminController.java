package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.DTO.OfficeBoyDTO;
import com.venturedive.rotikhilao.model.OfficeBoy;
import com.venturedive.rotikhilao.model.Order;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.service.admin.IAdminService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {

    private IAdminService adminService;

    @Autowired
    public AdminController(IAdminService iAdminService){
        this.adminService = iAdminService;
    }

    @GetMapping("/profile")
    public Void viewProfile(){
        //TODO
        adminService.viewProfile();
        return null;
    }

    @GetMapping("/workers")
    public ResponseList<OfficeBoy> getWorkers(){

        ResponseList<OfficeBoy> response = adminService.getAllWorkers();
        return response;

    }

    @PostMapping("/workers")
    public BooleanResponse createWorkerProfile(@RequestBody @Valid @NonNull OfficeBoyDTO request){

        BooleanResponse response = adminService.createWorkerProfile(request);
        return response;

    }

    @GetMapping("/workers/{workerId}/orders")
    public ResponseList<Order> getWorkerOrders(@PathVariable Long workerId){
        // service to fetch orders: should give a time range?
        ResponseList<Order> response = adminService.getWorkerOrders(workerId);
        return response;

    }

    @PatchMapping(value = "/orders/{orderId}")
    public BooleanResponse updateOrderStatus(Short statusId, @PathVariable(name = "orderId") Long orderId){

        // change order status
        return null;
    }

    @PostMapping(value = "/credit/{customerId}/recharge")
    public BooleanResponse rechargeAccount(@PathVariable("customerId") Long customerId, Integer credit){

        //// TODO
        return null;
    }

}
