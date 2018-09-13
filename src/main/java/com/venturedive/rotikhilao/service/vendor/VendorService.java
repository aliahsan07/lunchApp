package com.venturedive.rotikhilao.service.vendor;

import com.venturedive.rotikhilao.enums.FoodItemStatus;
import com.venturedive.rotikhilao.model.FoodItem;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.MenuResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.repository.FoodItemRepository;
import com.venturedive.rotikhilao.repository.VendorRepository;
import com.venturedive.rotikhilao.service.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class VendorService implements IVendorService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public MenuResponse displayMenu(Long vendorId) throws Exception {

        return serviceUtil.displayMenu(vendorId);

    }

    @Override
    public MenuResponse createMenu(ResponseList<FoodItem> items, Long vendorId) throws Exception {

        //1. do validation for vendor (TODO)
        //2. Iterate each item and add it to FoodItem table

        for (FoodItem item : items.getData()){
            item.setStatus(FoodItemStatus.ACTIVE.value());
            item.setVendorId(vendorId);
            foodItemRepository.save(item);
        }

        return displayMenu(vendorId);

    }

    @Override
    public BooleanResponse updateMenu(Long vendorId, Long itemId, FoodItem foodItem) throws Exception {

        // 1. access fooditem
        // 2. check if it belongs to the current vendor
        // 3. Respond accordingly

        FoodItem savedFoodItem = validateFoodItemChangeRequest(vendorId, itemId);

        if (foodItem.getPrice() != null){
            savedFoodItem.setPrice(foodItem.getPrice());
        }
        if (foodItem.getTitle() != null){
            savedFoodItem.setTitle(foodItem.getTitle());
        }

        foodItemRepository.save(savedFoodItem);
        return BooleanResponse.success();

    }

    private FoodItem validateFoodItemChangeRequest(Long vendorId, Long itemId) throws Exception {
        Optional<FoodItem> savedFoodItemWrapper = foodItemRepository.findById(itemId);

        if (!savedFoodItemWrapper.isPresent()){
            throw new Exception("Invalid itemId provided. No food item found");
        }

        FoodItem savedFoodItem = savedFoodItemWrapper.get();

        if (!savedFoodItem.getVendorId().equals(vendorId)){
            throw new Exception("Sorry! You're not authorized to update foodItem with id: " + itemId);
        }
        return savedFoodItem;
    }

    @Override
    public BooleanResponse deleteMenuItem(Long vendorId, Long itemId) throws Exception {

        FoodItem foodItem = validateFoodItemChangeRequest(vendorId, itemId);

        foodItem.setStatus(FoodItemStatus.DISCONTINUED.value());
        foodItemRepository.save(foodItem);

        return BooleanResponse.success();

    }
}
