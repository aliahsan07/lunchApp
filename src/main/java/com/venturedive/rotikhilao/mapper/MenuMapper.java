package com.venturedive.rotikhilao.mapper;

import com.venturedive.rotikhilao.DTO.FoodItemDTO;
import com.venturedive.rotikhilao.model.FoodItem;
import com.venturedive.rotikhilao.pojo.MenuResponse;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class MenuMapper {

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public static List<FoodItemDTO> wrapFoodItems(List<FoodItem> items){

        List<FoodItemDTO> wrappedFoodList = new ArrayList<>();

        if (items == null || items.isEmpty()){
            return wrappedFoodList;
        }

        for (FoodItem foodItem : items){
            FoodItemDTO foodItemDTO = new FoodItemDTO();
            foodItemDTO.setTitle(foodItem.getTitle());
            foodItemDTO.setUnitPrice(foodItem.getPrice());
            foodItemDTO.setVendor(foodItem.getVendorId());
            foodItemDTO.setItemId(foodItem.getId());
            wrappedFoodList.add(foodItemDTO);
        }


        return wrappedFoodList;
    }
}
