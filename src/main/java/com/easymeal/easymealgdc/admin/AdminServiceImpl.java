package com.easymeal.easymealgdc.admin;

import com.easymeal.easymealgdc.*;
import com.easymeal.easymealgdc.admin.departments.Departments;
import com.easymeal.easymealgdc.admin.departments.DepartmentsRepository;
import com.easymeal.easymealgdc.admin.positions.Positions;
import com.easymeal.easymealgdc.admin.positions.PositionsRepository;
import com.easymeal.easymealgdc.admin.staff.StaffInfoRepository;
import com.easymeal.easymealgdc.authentication.entity.PersonDetails;
import com.easymeal.easymealgdc.admin.staff.StaffInfo;
import com.easymeal.easymealgdc.authentication.service_class.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final FormatterHelper formatterHelper = new FormatterHelper();
    private final DepartmentsRepository departmentsRepository;
    private final PositionsRepository positionsRepository;
    private final StaffInfoRepository staffInfoRepository;
    private final PersonService personService;
    @Autowired
    public JavaMailSender emailSender;

    @Override
    public Results addDepartment(DbDepartment dbDepartment) {

        String name = dbDepartment.getName();

        Departments departments = new Departments();
        departments.setName(name);

        Boolean isDepartment = departmentsRepository.existsByName(name);
        if (isDepartment){
            // Update
            return new Results(200, departments);
        } else {
            //Add
            Departments addedDepartments = departmentsRepository.save(departments);
            return new Results(201, addedDepartments);
        }

    }

    @Override
    public Results viewDepartments(int limit, int page) {

        List<Departments> departmentsList = departmentsRepository.findAll();
        return new Results(200, departmentsList);
    }

    @Override
    public Results deleteDepartment(String departmentId) {
        Departments departments = getDepartment(departmentId);
        if (departments == null) return new Results(400, "Department is not valid.");

        departmentsRepository.delete(departments);

        return new Results(200, new DbResults("Department deleted successfully."));
    }


    private Departments getDepartment(String id){
        Optional<Departments> optionalDepartments = departmentsRepository.findById(id);
        return optionalDepartments.orElse(null);
    }

    @Override
    public Results addPositions(DbPositions dbPositions) {

        String id =dbPositions.getId();
        String name = dbPositions.getName();
        String departmentId = dbPositions.getDepartmentId();

        Departments departments = getDepartment(departmentId);
        if (departments == null) return new Results(400, "Department is not valid.");

        boolean isName = positionsRepository.existsByName(name);
        if (isName) return new Results(400, "Position is in the system.");

        Positions positions = new Positions();
        positions.setName(name);
        positions.setDepartment(departments);

        Positions addedPositions = positionsRepository.save(positions);
        return new Results(201, addedPositions);

    }

    @Override
    public Results viewPositions(int limit, int page, String departmentId) {
        Departments departments = getDepartment(departmentId);
        if (departments == null) return new Results(400, "Department is not valid.");

        List<Positions> positionsList = positionsRepository.findByDepartment(departments);
        return new Results(200, positionsList);
    }

    @Override
    public Results addStaff(DbAddStaff dbAddStaff) {

        String name = dbAddStaff.getName();
        String phoneNumber = dbAddStaff.getPhoneNumber();
        String emailAddress = dbAddStaff.getEmailAddress();
        String profileUrl = dbAddStaff.getProfileUrl();
        String departmentId = dbAddStaff.getDepartmentId();
        String positionId = dbAddStaff.getPositionId();

        Optional<Departments> optionalDepartments = departmentsRepository.findById(departmentId);
        Optional<Positions> optionalPositions = positionsRepository.findById(positionId);

        if (optionalDepartments.isEmpty()) return new Results(400, "Department is not valid.");
        if (optionalPositions.isEmpty()) return new Results(400, "Position is not valid.");

        String password = formatterHelper.generateOtp();

        DbRegister dbRegister = new DbRegister(
                name,
                emailAddress,
                password,
                password,
                phoneNumber,
                true);

        Results results = personService.registerAccount(dbRegister, "STAFF");
        int code = results.getStatusCode();
        if (code == 201){
            PersonDetails personDetails = personService.getPersonByEmailAddress(emailAddress);
            if (personDetails != null){
                String userId = personDetails.getUserId();

                StaffInfo staffInfo = new StaffInfo();
                staffInfo.setUserId(userId);
                staffInfo.setProfileUrl(profileUrl);
                staffInfo.setPosition(optionalPositions.get());

                staffInfoRepository.save(staffInfo);
                String subject = "Staff Account";
                String message = "Use this password to access your account.\n"+password;

                //Send mail
                formatterHelper.sendOtpMail(
                        emailSender, emailAddress, message, subject);
                return new Results(200, new DbResults("Staff registered successfully."));

            }
        }


        return null;
    }

    @Override
    public Results deactivateStaff(String staffId) {
        return null;
    }

    @Override
    public Results getStats(int limit, int page) {
        return null;
    }

    @Override
    public Results viewStaff(int limit, int page) {
        return personService.getStaff(true);
    }
}
