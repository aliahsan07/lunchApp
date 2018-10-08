package com.venturedive.rotikhilao.request;

import com.venturedive.rotikhilao.DTO.FoodItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderWrapper {

    private List<FoodItemDTO> orderList;

    private Integer totalPrice;

    private Integer noOfItems;

    private Long customerId;

    private String token;
}
