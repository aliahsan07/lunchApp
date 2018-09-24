package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.enums.OrderStatus;
import com.venturedive.rotikhilao.model.Customer;
import com.venturedive.rotikhilao.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//    @Query("From Order o where o.assignedTo = :workerId and o.orderStatus = :status and o.orderTime between :fromTime and :toTime")
//    List<Order> findAllByAssignedToAndStatusAndDeliveryTime(@Param("workerId") Long workerId, @Param("status") Integer status,
//                                                            @Param("fromTime")LocalDateTime fromTime, @Param("toTime") LocalDateTime toTime);

    List<Order> findAllByOrderedByIdAndOrderStatusOrderByOrderTimeDesc(@Param("orderedBy") Long orderedById, @Param("status") Short status);

    List<Order> findAllByOrderedByIdOrderByOrderTimeDesc(@Param("orderedBy") Long orderedBy);


}
