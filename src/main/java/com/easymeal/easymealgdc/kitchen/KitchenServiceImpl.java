package com.easymeal.easymealgdc.kitchen;

import com.easymeal.easymealgdc.DbAddMenu;
import com.easymeal.easymealgdc.DbAddMenuItems;
import com.easymeal.easymealgdc.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService{
    @Override
    public Results addMenu(DbAddMenu dbAddMenu) {
        return null;
    }

    @Override
    public Results addMenuItems(DbAddMenuItems dbAddMenuItems) {
        return null;
    }

    @Override
    public Results getMenu(int limit, int pageNo) {
        return null;
    }

    @Override
    public Results getMenuItems(String menuId, int limit, int pageNo) {
        return null;
    }
}
