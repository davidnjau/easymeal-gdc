package com.easymeal.easymealgdc.authentication.repository;

import com.easymeal.easymealgdc.authentication.entity.PersonDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDetailsRepository extends JpaRepository<PersonDetails, String > {

    PersonDetails findAllByEmailAddress(String email);
    Boolean existsByEmailAddress(String emailAddress);
    Boolean existsByPhoneNumber(String phoneNumber);
    PersonDetails findAllByUserId(String userId);
}
