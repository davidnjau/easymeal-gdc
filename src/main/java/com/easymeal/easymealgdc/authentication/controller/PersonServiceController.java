package com.easymeal.easymealgdc.authentication.controller;

import com.easymeal.easymealgdc.*;
import com.easymeal.easymealgdc.authentication.service_class.service.PersonService;
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

    @PostMapping(path = "register-account")
    public ResponseEntity<?> registerAccount(@RequestBody DbRegister dbRegister){
        Results results = personService.registerAccount(dbRegister);
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

}
