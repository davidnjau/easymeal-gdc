package com.easymeal.easymealgdc.authentication.repository;

import com.easymeal.easymealgdc.authentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long > {

    Role findByName(String name);
    Boolean existsByName(String roleName);

}
