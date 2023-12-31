package com.easymeal.easymealgdc.authentication.service_class.service;

import com.easymeal.easymealgdc.*;
import com.easymeal.easymealgdc.DbRegister;
import com.easymeal.easymealgdc.DbRequestPasswordChange;
import com.easymeal.easymealgdc.Results;
import com.easymeal.easymealgdc.authentication.entity.PersonDetails;

public interface PersonService {
    PersonDetails getPersonByEmailAddress(String emailAddress);
    Results registerAccount(DbRegister dbRegister, String type);
    Results requestPasswordChange(DbRequestPasswordChange dbRequestPasswordChange);
    Results changePassword(DbPasswordChange dbPasswordChange);
    Results getStaff(boolean isStaff);
    PersonDetails getStaffById(String id);
    PersonDetails updateDetails(PersonDetails personDetails);

}
