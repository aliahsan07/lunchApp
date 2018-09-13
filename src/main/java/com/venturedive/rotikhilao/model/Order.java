package com.venturedive.rotikhilao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="orders")
@Data
public class Order implements Serializable {


    private static final long serialVersionUID = 3308888085412845774L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="total_price")
    private Integer totalPrice;

    @Column(name="order_time")
    private LocalDateTime orderTime;

    @Column(name="delivery_time")
    private LocalDateTime deliveryTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", insertable = false, updatable = false)
    @JsonIgnore
    private Customer orderedBy;

    @Column(name="ordered_by")
    private Long orderedById;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", insertable = false, updatable = false)
    @JsonIgnore
    private Customer orderedFor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", insertable = false, updatable = false)
    @JsonIgnore
    private OfficeBoy assignedTo;

    @Column(name="order_status")
    private Short orderStatus;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> items = new ArrayList<>();

    public void addFoodItem(FoodItem foodItem, Integer quantity){
        OrderItem orderItem = new OrderItem(this, foodItem, quantity);
        items.add(orderItem);
        foodItem.getItems().add(orderItem);
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || o.getClass() != o.getClass()){
            return false;
        }
        Order that = (Order) o;
        return Objects.equals(orderedBy.getId(), that.getId()) && Objects.equals(orderTime, that.getOrderTime());
    }

    @Override
    public int hashCode(){
        return Objects.hash(orderedBy.getId(), orderTime);
    }

//    public void removeFoodItem(FoodItem foodItem){
//        OrderItem orderItem = new OrderItem(this, foodItem, 0);
//        foodItem.getItems().remove()
//    }
}
