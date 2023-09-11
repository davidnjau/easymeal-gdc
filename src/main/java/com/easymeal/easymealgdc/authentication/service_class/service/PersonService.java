package com.easymeal.easymealgdc.authentication.service_class.service;

import com.easymeal.easymealgdc.authentication.entity.PersonDetails;

public interface PersonService {
    PersonDetails getPersonByEmailAddress(String emailAddress);
}
