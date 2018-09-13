package com.venturedive.rotikhilao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.venturedive.rotikhilao.DTO.FoodItemDTO;
import com.venturedive.rotikhilao.model.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MenuResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<FoodItemDTO> items;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String vendorName;

    public MenuResponse(){
        items = new ArrayList<>();
    }
}
