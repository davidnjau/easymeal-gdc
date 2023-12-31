package com.easymeal.easymealgdc.authentication.service_class.service;


import com.easymeal.easymealgdc.DbRole;
import com.easymeal.easymealgdc.Results;
import com.easymeal.easymealgdc.authentication.entity.Role;

public interface RoleService {

    boolean isRoleExists(String roleName);
    Role addRole(Role role);
    Role getRoleDetails(String roleName);
    Results addRoleData(DbRole dbRole);
    Results getAllRoles();

}
