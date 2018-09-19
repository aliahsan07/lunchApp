package com.venturedive.rotikhilao.service.util;

import com.venturedive.rotikhilao.enums.FoodItemStatus;
import com.venturedive.rotikhilao.mapper.MenuMapper;
import com.venturedive.rotikhilao.model.FoodItem;
import com.venturedive.rotikhilao.model.Vendor;
import com.venturedive.rotikhilao.pojo.MenuResponse;
import com.venturedive.rotikhilao.repository.FoodItemRepository;
import com.venturedive.rotikhilao.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUtil {


    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private VendorRepository vendorRepository;


    public MenuResponse displayMenu(Long vendorId) throws Exception {

        List<FoodItem> activeMenuItems = foodItemRepository.findAllByVendorIdAndStatus(vendorId, FoodItemStatus.ACTIVE.value());
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);


        if (!vendor.isPresent()) {
            throw new Exception("No data present against the provided vendor ID");
        }

        MenuResponse menuResponse = new MenuResponse();

        menuResponse.setItems(MenuMapper.wrapFoodItems(activeMenuItems));
        if (vendor.get().getName() != null) {
            menuResponse.setVendorName(vendor.get().getName());
        }
        return menuResponse;
    }

}