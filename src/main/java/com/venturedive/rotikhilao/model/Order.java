package com.venturedive.rotikhilao.model;

import com.venturedive.rotikhilao.enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="[total_price]")
    private Integer totalPrice;

    @Column(name="[order_time]")
    private LocalDateTime orderTime;

    @Column(name="[delivery_time]")
    private LocalDateTime deliveryTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", insertable = false, updatable = false)
    private Customer orderedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", insertable = false, updatable = false)
    private Customer orderedFor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", insertable = false, updatable = false)
    private OfficeBoy assignedTo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="[order_status]")
    private OrderStatus orderStatus;

}
