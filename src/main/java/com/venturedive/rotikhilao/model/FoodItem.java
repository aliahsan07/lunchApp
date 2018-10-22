package com.venturedive.rotikhilao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name="food_item")
@Entity
@Data
public class FoodItem implements Serializable {

    private static final long serialVersionUID = -2140682219825708417L;

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

    @Column(name="image_url")
    private String imageUrl;

    @OneToMany(
            mappedBy = "item",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<OrderItem> items = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FoodItem that = (FoodItem) o;
        return Objects.equals(title, that.title) && Objects.equals(vendorId, that.vendorId);

    }

    @Override
    public int hashCode() {
        return Objects.hash( vendorId, title);
    }

}
