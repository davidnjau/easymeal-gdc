package com.easymeal.easymealgdc.authentication.service_class.service;

import com.easymeal.easymealgdc.DbPasswordChange;
import com.easymeal.easymealgdc.DbRegister;
import com.easymeal.easymealgdc.DbRequestPasswordChange;
import com.easymeal.easymealgdc.Results;
import com.easymeal.easymealgdc.authentication.entity.PersonDetails;

public interface PersonService {
    PersonDetails getPersonByEmailAddress(String emailAddress);
    Results registerAccount(DbRegister dbRegister);
    Results requestPasswordChange(DbRequestPasswordChange dbRequestPasswordChange);
    Results changePassword(DbPasswordChange dbPasswordChange);
}
