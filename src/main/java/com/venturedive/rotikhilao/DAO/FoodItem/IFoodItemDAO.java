package com.venturedive.rotikhilao.DAO.FoodItem;

import com.venturedive.rotikhilao.model.FoodItem;

import java.util.List;

public interface IFoodItemDAO {

    public abstract FoodItem fetchFoodItemById(Long id) throws Exception;

    public abstract List<FoodItem> findAllItemsInPriceRange(Integer fromPrice, Integer toPrice);

    public abstract void saveFoodItem(FoodItem foodItem);
}
