package com.easymeal.easymealgdc.authentication.service_class.impl;

import com.easymeal.easymealgdc.DbPasswordChange;
import com.easymeal.easymealgdc.DbRegister;
import com.easymeal.easymealgdc.DbRequestPasswordChange;
import com.easymeal.easymealgdc.Results;
import com.easymeal.easymealgdc.authentication.entity.PersonDetails;
import com.easymeal.easymealgdc.authentication.entity.Role;
import com.easymeal.easymealgdc.authentication.repository.PersonDetailsRepository;
import com.easymeal.easymealgdc.authentication.repository.RoleRepository;
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
    private final RoleRepository roleRepository;

    @Override
    public boolean isRoleExists(String roleName) {
        return roleRepository.existsByName(roleName);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleDetails(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public PersonDetails getPersonByEmailAddress(String emailAddress) {
        Optional<PersonDetails> optionalPersonDetails =
                personDetailsRepository.findAllByEmailAddress(emailAddress);
        return optionalPersonDetails.orElse(null);
    }

    @Override
    public Results registerAccount(DbRegister dbRegister) {
        return null;
    }

    @Override
    public Results requestPasswordChange(DbRequestPasswordChange dbRequestPasswordChange) {
        return null;
    }

    @Override
    public Results changePassword(DbPasswordChange dbPasswordChange) {
        return null;
    }


}
