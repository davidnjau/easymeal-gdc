package com.easymeal.easymealgdc.kitchen;

import com.easymeal.easymealgdc.DbAddMenu;
import com.easymeal.easymealgdc.DbAddMenuItems;
import com.easymeal.easymealgdc.Results;
import com.easymeal.easymealgdc.kitchen.menu.Menu;
import com.easymeal.easymealgdc.kitchen.menu.MenuItem;
import com.easymeal.easymealgdc.kitchen.menu.MenuItemRepository;
import com.easymeal.easymealgdc.kitchen.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService{

    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public Results addMenu(DbAddMenu dbAddMenu) {

        String id = dbAddMenu.getId();
        String name = dbAddMenu.getName();
        String startPeriod = dbAddMenu.getStartPeriod();
        String endPeriod = dbAddMenu.getEndPeriod();

        //Check if menu has been added
        Optional<Menu> optionalMenu = menuRepository.findByName(name);
        if (optionalMenu.isPresent())
            return new Results(400, "Menu already exists. ");

        Menu menu = new Menu();
        menu.setName(name);
        menu.setStartPeriod(startPeriod);
        menu.setEndPeriod(endPeriod);

        menuRepository.save(menu);

        return new Results(201, menu);
    }

    @Override
    public Results addMenuItems(DbAddMenuItems dbAddMenuItems) {

        String id = dbAddMenuItems.getId();
        String name = dbAddMenuItems.getName();
        String menuId = dbAddMenuItems.getMenuId();
        String imageUrl = dbAddMenuItems.getImageUrl();
        int price = dbAddMenuItems.getPrice();

        //Check if menu has been added
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findByName(name);
        if (optionalMenuItem.isPresent())
            return new Results(400, "Menu item already exists. ");

        Menu menu = getMenu(menuId);
        if (menu == null) return new Results(400, "Menu is not valid");

        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setImageUrl(imageUrl);
        menuItem.setMenu(menu);
        menuItem.setPrice(price);

        menuItemRepository.save(menuItem);

        return new Results(201, menuItem);
    }

    private Menu getMenu(String id){
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        return optionalMenu.orElse(null);
    }
    private MenuItem getMenuItem(String id){
        Optional<MenuItem> optionalMenu = menuItemRepository.findById(id);
        return optionalMenu.orElse(null);
    }

    @Override
    public Results getMenuList(int limit, int pageNo) {

        List<Menu> menuList = menuRepository.findAll();
        return new Results(200, menuList);

    }

    @Override
    public Results getMenuItems(String menuId, int limit, int pageNo) {

        Menu menu = getMenu(menuId);
        if (menu == null) return new Results(400, "Menu is not valid");

        List<MenuItem> menuItemList = menuItemRepository.findByMenu(menu);
        return new Results(200, menuItemList);

    }

    @Override
    public Results getMenuData(String menuId) {
        Menu menu = getMenu(menuId);
        if (menu != null)
            return new Results(200, menu);

        return new Results(400, "Menu id is not valid");
    }

    @Override
    public Results getMenuItemData(String menuId) {
        MenuItem menu = getMenuItem(menuId);
        if (menu != null)
            return new Results(200, menu);

        return new Results(400, "Menu item id is not valid");
    }
}
