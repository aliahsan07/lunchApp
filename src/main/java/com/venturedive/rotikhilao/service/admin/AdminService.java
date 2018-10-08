package com.venturedive.rotikhilao.service.admin;

import com.venturedive.rotikhilao.DTO.OfficeBoyDTO;
import com.venturedive.rotikhilao.model.Admin;
import com.venturedive.rotikhilao.model.Customer;
import com.venturedive.rotikhilao.model.OfficeBoy;
import com.venturedive.rotikhilao.model.Order;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AdminService implements IAdminService {

    @Autowired
    private OfficeBoyRepository officeBoyRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    //TODO: validation checks and error handling

    @Override
    public ResponseList<OfficeBoy> getAllWorkers() {

        List<OfficeBoy> workers = officeBoyRepository.findAll();
        ResponseList<OfficeBoy> response = new ResponseList<>();
        response.setData(workers);
        return response;

    }

    @Override
    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public BooleanResponse createWorkerProfile(OfficeBoyDTO request) {

        OfficeBoy officeBoy = new OfficeBoy();

        Admin admin = adminRepository.getOne(1L);

//        officeBoy.setFirstName(request.getFirstName());
//        officeBoy.setLastName(request.getLastName());
        officeBoy.setUserName(request.getUserName());
        //officeBoy.setPassword(request.getPassword());
        officeBoy.setCreatedBy(admin.getId());

        officeBoyRepository.save(officeBoy);
        return BooleanResponse.success();
    }

    @Override
    public ResponseList<Order> getWorkerOrders(Long workerId) {

        LocalDateTime fromTime = LocalDateTime.now().minusHours(5);
        LocalDateTime toTime = LocalDateTime.now();
        //List<Order> orders = orderRepository.findAllByAssignedToAndStatusAndDeliveryTime(workerId, OrderStatus.PREPARING.ordinal(),
        //                                                                                fromTime, toTime);

        // handling and return object.

        return null;

    }

    @Override
    public Void viewProfile() {
        // TODO
        return null;

    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public BooleanResponse rechargeAccount(Long customerId, Integer credit) throws Exception {

        // 1. fetch customer entity from database
        Customer customer = customerRepository.getOne(customerId);
        // 2. Throw error if not found, otherwise update user's balance
        if (customer == null){
            throw new Exception("Sorry! No customer exists against the provided customer ID");
        }

        customer.setBalance(customer.getBalance() + credit);

        return  BooleanResponse.success();


    }

}
