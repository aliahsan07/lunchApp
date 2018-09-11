package com.venturedive.rotikhilao.model;

import lombok.Data;

import javax.persistence.*;

@Table(name="food_item")
@Entity
@Data
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="price")
    private Integer price;

    @Column(name="vendor_id")
    private Long vendorId;

    @Column(name="status")
    private Short status;

}
