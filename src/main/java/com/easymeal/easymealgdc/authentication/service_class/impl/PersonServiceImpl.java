package com.easymeal.easymealgdc.authentication.service_class.impl;

import com.easymeal.easymealgdc.authentication.entity.PersonDetails;
import com.easymeal.easymealgdc.authentication.entity.Role;
import com.easymeal.easymealgdc.authentication.repository.PersonDetailsRepository;
import com.easymeal.easymealgdc.authentication.service_class.service.PersonService;
import com.easymeal.easymealgdc.authentication.service_class.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, RoleService {

    private final PersonDetailsRepository personDetailsRepository;
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

    @Override
    public PersonDetails getPersonByEmailAddress(String emailAddress) {
        Optional<PersonDetails> optionalPersonDetails =
                personDetailsRepository.findAllByEmailAddress(emailAddress);
        return optionalPersonDetails.orElse(null);
    }
}
