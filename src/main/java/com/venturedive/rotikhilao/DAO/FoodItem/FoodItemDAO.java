package com.venturedive.rotikhilao.DAO.FoodItem;

import com.venturedive.rotikhilao.enums.FoodItemStatus;
import com.venturedive.rotikhilao.model.FoodItem;
import com.venturedive.rotikhilao.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FoodItemDAO implements IFoodItemDAO {


    @Autowired
    FoodItemRepository foodItemRepository;

    @Override
    public FoodItem fetchFoodItemById(Long id) throws Exception {

        FoodItem foodItem = null;
        try{
            foodItem = foodItemRepository.getOne(id);
        } catch (Exception e){
            throw new Exception("Invalid FoodId provided");
        }

        return foodItem;

    }

    @Override
    public List<FoodItem> findAllItemsInPriceRange(Integer fromPrice, Integer toPrice) {

        Short status = FoodItemStatus.ACTIVE.value();
        List<FoodItem> items = foodItemRepository.findAllByPriceBetweenAndStatus(fromPrice, toPrice, FoodItemStatus.ACTIVE.value());

        return items;
    }
}
