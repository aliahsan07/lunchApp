package com.venturedive.rotikhilao.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer quantity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer unitPrice;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPrice;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long itemId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long orderItemId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long vendor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
}
