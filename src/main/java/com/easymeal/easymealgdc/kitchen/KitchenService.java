package com.easymeal.easymealgdc.kitchen;


import com.easymeal.easymealgdc.DbAddMenu;
import com.easymeal.easymealgdc.DbAddMenuItems;
import com.easymeal.easymealgdc.Results;

public interface KitchenService {

    Results addMenu(DbAddMenu dbAddMenu);
    Results addMenuItems(DbAddMenuItems dbAddMenuItems);
    Results getMenuList(int limit, int pageNo);
    Results getMenuItems(String menuId, int limit, int pageNo);
    Results getMenuData(String menuId);
    Results getMenuItemData(String menuId);
}
