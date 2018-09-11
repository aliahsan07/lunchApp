package com.venturedive.rotikhilao.service.admin;

import com.venturedive.rotikhilao.DTO.OfficeBoyDTO;
import com.venturedive.rotikhilao.model.OfficeBoy;
import com.venturedive.rotikhilao.model.Order;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;

public interface IAdminService {

    ResponseList<OfficeBoy> getAllWorkers();

    BooleanResponse createWorkerProfile(OfficeBoyDTO request);

    ResponseList<Order> getWorkerOrders(Long workerId);

    Void viewProfile();

    BooleanResponse rechargeAccount(Long customerId, Integer credit) throws Exception;
}
