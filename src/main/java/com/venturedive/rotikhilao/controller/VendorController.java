package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.model.FoodItem;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.MenuResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.request.OrderWrapper;
import com.venturedive.rotikhilao.service.vendor.IVendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@RequestMapping("/api/vendors")
public class VendorController {

    // 1. view current menu
    // 2. create new menu
    // 3. update menu
    // 4. remove from menu
    // 5. revenue related?

    @Autowired
    private IVendorService vendorService;

    @GetMapping(value = "/{vendorId}/menu")
    public MenuResponse displayMenu(@PathVariable(value="vendorId") Long vendorId ) throws Exception {
        // should be visible for customers as well
        log.info("DISPLAY MENU REQUEST RECEIVED");

        return vendorService.displayMenu(vendorId);

    }


    @PostMapping(value = "/{vendorId}/menu")
    public MenuResponse createMenu(@RequestBody @NotNull @Valid ResponseList<FoodItem> items, @RequestParam(value="vendorId") Long vendorId) throws Exception {

        log.info("CREATE MENU REQUEST RECEIVED");

        return vendorService.createMenu(items, vendorId);
    }

    @PatchMapping(value = "/{vendorId}/menu/{itemId}")
    public BooleanResponse updateMenuItem(@RequestBody @NotNull @Valid FoodItem foodItem,
                                          @PathVariable(name="vendorId") Long vendorId,
                                          @PathVariable(name="itemId") Long itemId) throws Exception {

        log.info("UPDATE MENU REQUEST RECEIVED");

        return vendorService.updateMenu(vendorId, itemId, foodItem);
    }

    @PatchMapping(value="/{vendorId}/menu/{itemId}/delete")
    public BooleanResponse deleteMenuItem(@PathVariable(name="vendorId") Long vendorId,
                                          @PathVariable(name="itemId") Long itemId) throws Exception {

        log.info("DELETE MENU ITEM REQUEST RECEIVED");

        return vendorService.deleteMenuItem(vendorId, itemId);
    }

}
