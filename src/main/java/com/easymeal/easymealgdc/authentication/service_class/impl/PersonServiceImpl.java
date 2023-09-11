package com.easymeal.easymealgdc.authentication.service_class.impl;

import com.easymeal.easymealgdc.authentication.entity.Role;
import com.easymeal.easymealgdc.authentication.service_class.service.PersonService;
import com.easymeal.easymealgdc.authentication.service_class.service.RoleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService, RoleService {
    @Override
    public boolean isRoleExists(String roleName) {
        return false;
    }

    @Override
    public Role addRole(Role role) {
        return null;
    }

    @Override
    public Role getRoleDetails(String roleName) {
        return null;
    }
}
