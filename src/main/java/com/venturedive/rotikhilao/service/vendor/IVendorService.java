package com.venturedive.rotikhilao.service.vendor;

import com.venturedive.rotikhilao.model.FoodItem;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.MenuResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;

public interface IVendorService {
    MenuResponse displayMenu(Long vendorId) throws Exception;

    MenuResponse createMenu(ResponseList<FoodItem> items, Long vendorId) throws Exception;

    BooleanResponse updateMenu(Long vendorId, Long itemId, FoodItem foodItem) throws Exception;

    BooleanResponse deleteMenuItem(Long vendorId, Long itemId) throws Exception;
}
