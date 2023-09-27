package com.easymeal.easymealgdc.kitchen;

import com.easymeal.easymealgdc.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kitchen/api/v1/")
public class KitchenController {

    private final FormatterHelper formatterHelper = new FormatterHelper();
    private final KitchenService kitchenService;

    @RequestMapping(value = "admin-menu", method = {RequestMethod.PATCH, RequestMethod.POST})
    public ResponseEntity<?> addMenu(@RequestBody DbAddMenu dbAddMenu){
        Results results = kitchenService.addMenu(dbAddMenu);
        return formatterHelper.getResponseEntity(results);
    }

    @RequestMapping(value = "admin-menu-item", method = {RequestMethod.PATCH, RequestMethod.POST})
    public ResponseEntity<?> addMenuItem(@RequestBody DbAddMenuItems dbAddMenuItems){
        Results results = kitchenService.addMenuItems(dbAddMenuItems);
        return formatterHelper.getResponseEntity(results);
    }

    @RequestMapping(value = "get-menu", method = {RequestMethod.GET})
    public ResponseEntity<?> getMenus(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int page
    ){
        Results results = kitchenService.getMenuList(limit, page);
        return formatterHelper.getResponseEntity(results);
    }

    @RequestMapping(value = "get-menu/{menuId}", method = {RequestMethod.GET})
    public ResponseEntity<?> getMenu(
            @PathVariable("menuId") String menuId
    ){
        Results results = kitchenService.getMenuData(menuId);
        return formatterHelper.getResponseEntity(results);
    }

    @RequestMapping(value = "get-menu-item-details/{menuId}", method = {RequestMethod.GET})
    public ResponseEntity<?> getMenuItemDetails(
            @PathVariable("menuId") String menuId
    ){
        Results results = kitchenService.getMenuItemData(menuId);
        return formatterHelper.getResponseEntity(results);
    }

    @RequestMapping(value = "get-menu-items/{menuId}", method = {RequestMethod.GET})
    public ResponseEntity<?> getMenuItems(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int page,
            @PathVariable("menuId") String menuId
    ){
        Results results = kitchenService.getMenuItems(menuId,limit, page);
        return formatterHelper.getResponseEntity(results);
    }




}
