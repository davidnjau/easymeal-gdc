package com.easymeal.easymealgdc.authentication.service_class.impl;

import com.easymeal.easymealgdc.*;
import com.easymeal.easymealgdc.authentication.entity.PersonDetails;
import com.easymeal.easymealgdc.authentication.entity.Role;
import com.easymeal.easymealgdc.authentication.repository.PersonDetailsRepository;
import com.easymeal.easymealgdc.authentication.repository.RoleRepository;
import com.easymeal.easymealgdc.authentication.service_class.service.PersonService;
import com.easymeal.easymealgdc.authentication.service_class.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, RoleService {

    private final PersonDetailsRepository personDetailsRepository;
    private final RoleRepository roleRepository;
    private final FormatterHelper formatterHelper = new FormatterHelper();
    private final PasswordEncoder passwordEncoder;

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
    public Results addRoleData(DbRole dbRole) {

        String roleName = dbRole.getName();
        if (isRoleExists(roleName)) return new Results(400, "Role exists.");

        Role role = new Role(roleName);
        Role addedRole = addRole(role);
        if (addedRole == null) return new Results(400, "Role not added.");

        return new Results(201, addedRole);
    }

    @Override
    public Results getAllRoles() {
        return new Results(200, roleRepository.findAll());
    }

    @Override
    public PersonDetails getPersonByEmailAddress(String emailAddress) {
        Optional<PersonDetails> optionalPersonDetails =
                personDetailsRepository.findAllByEmailAddress(emailAddress);
        return optionalPersonDetails.orElse(null);
    }

    @Override
    public Results registerAccount(DbRegister dbRegister, String role) {

        String name = dbRegister.getName();
        String emailAddress = dbRegister.getEmailAddress();
        String password = dbRegister.getPassword();
        String confirmPassword = dbRegister.getConfirmPassword();
        String phoneNumber = dbRegister.getPhoneNumber();

        if (name.isEmpty()) return new Results(400, "Invalid name");
        if (!password.equals(confirmPassword)) return new Results(400, "Password should match");
        if (!formatterHelper.isEmailValid(emailAddress)) return new Results(400, "Invalid email address");
        if (!formatterHelper.isValidPhoneNumber(phoneNumber)) return new Results(400, "Invalid phone number");

        PersonDetails personDetails = new PersonDetails();

        if (personDetailsRepository.existsByEmailAddress(emailAddress) ||
                personDetailsRepository.existsByPhoneNumber(phoneNumber))
            return new Results(400, "User exists");

        personDetails.setEmailAddress(emailAddress);
        personDetails.setPersonName(name);
        personDetails.setVerified(true);
        personDetails.setPhoneNumber(phoneNumber);

        String encryptedPassword = passwordEncoder.encode(password);
        personDetails.setPassword(encryptedPassword);

        addUser(personDetails, role);

        return new Results(201, new DbResults("User registered successfully."));
    }



    private PersonDetails addUser(PersonDetails personDetails, String role){
        Role roles = getRoleDetails("ROLE_"+role);
        personDetails.getRolesCollection().add(roles);
        if (role.equals("STAFF")){
            personDetails.setStaff(true);
        }else {
            personDetails.setStaff(false);
        }
        return personDetailsRepository.save(personDetails);
    }



    @Override
    public Results requestPasswordChange(DbRequestPasswordChange dbRequestPasswordChange) {
        return null;
    }

    @Override
    public Results changePassword(DbPasswordChange dbPasswordChange) {
        return null;
    }

    @Override
    public Results getStaff(boolean isStaff) {

        List<PersonDetails> personDetailsList = personDetailsRepository.findByIsStaff(isStaff);
        return new Results(200, personDetailsList);
    }


}
