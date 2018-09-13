package com.venturedive.rotikhilao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="order_item")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem implements Serializable {


    private static final long serialVersionUID = 4801325444819644742L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private FoodItem item;

    @Column(name="quantity")
    private Integer quantity; // the reason I had to create this Entity

    public OrderItem(Order order, FoodItem foodItem, Integer quantity){
        this.item = foodItem;
        this.quantity = quantity;
        this.order = order;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderItem that = (OrderItem) o;
        return Objects.equals(order, that.order) && Objects.equals(item, that.item);

    }

    @Override
    public int hashCode(){
        return Objects.hash(order, item);
    }
}
