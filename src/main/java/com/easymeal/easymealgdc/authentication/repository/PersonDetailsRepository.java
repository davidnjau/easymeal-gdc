package com.easymeal.easymealgdc.authentication.repository;

import com.easymeal.easymealgdc.authentication.entity.PersonDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PersonDetailsRepository extends JpaRepository<PersonDetails, String > {

    Optional<PersonDetails> findAllByEmailAddress(String email);
    Boolean existsByEmailAddress(String emailAddress);
    Boolean existsByPhoneNumber(String phoneNumber);
    PersonDetails findAllByUserId(String userId);
}
