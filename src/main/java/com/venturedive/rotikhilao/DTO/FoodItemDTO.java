package com.venturedive.rotikhilao.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDTO {

    private Integer quantity;

    private Integer unitPrice;

    private Integer totalPrice;

    private Long itemId;
}
