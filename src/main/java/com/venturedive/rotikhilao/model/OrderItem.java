package com.venturedive.rotikhilao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    private FoodItem item;

    @Column(name="quantity")
    private Integer quantity; // the reason I had to create this Entity
}
