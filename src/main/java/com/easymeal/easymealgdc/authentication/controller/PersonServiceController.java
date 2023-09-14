package com.easymeal.easymealgdc.authentication.controller;

import com.easymeal.easymealgdc.*;
import com.easymeal.easymealgdc.authentication.entity.Role;
import com.easymeal.easymealgdc.authentication.service_class.service.PersonService;
import com.easymeal.easymealgdc.authentication.service_class.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/api/v1/")
public class PersonServiceController {

    private final FormatterHelper formatterHelper = new FormatterHelper();

    private final PersonService personService;
    private final RoleService roleService;

    @PostMapping(path = "system-register-account")
    public ResponseEntity<?> registerSystemAccount(@RequestBody DbRegister dbRegister){
        Results results = personService.registerAccount(dbRegister, "SYSTEM");
        return formatterHelper.getResponseEntity(results);
    }
    @PostMapping(path = "register-account")
    public ResponseEntity<?> registerAccount(@RequestBody DbRegister dbRegister){
        Results results = personService.registerAccount(dbRegister, "USER");
        return formatterHelper.getResponseEntity(results);
    }
    @PostMapping(path = "request-password-change")
    public ResponseEntity<?> requestPasswordChange(@RequestBody DbRequestPasswordChange dbRequestPasswordChange){
        Results results = personService.requestPasswordChange(dbRequestPasswordChange);
        return formatterHelper.getResponseEntity(results);
    }
    @PostMapping(path = "change-password")
    public ResponseEntity<?> changePassword(@RequestBody DbPasswordChange dbPasswordChange){
        Results results = personService.changePassword(dbPasswordChange);
        return formatterHelper.getResponseEntity(results);
    }
    @PostMapping(path = "add-role")
    public ResponseEntity<?> addRole(@RequestBody DbRole dbRole){
        Results results = roleService.addRoleData(dbRole);
        return formatterHelper.getResponseEntity(results);
    }
    @PostMapping(path = "get-roles")
    public ResponseEntity<?> getRoles(){
        Results results = roleService.getAllRoles();
        return formatterHelper.getResponseEntity(results);
    }

}
