package com.easymeal.easymealgdc.kitchen.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, String> {
    List<MenuItem> findByMenu(Menu menu);
    Optional<MenuItem> findByName(String name);
}
