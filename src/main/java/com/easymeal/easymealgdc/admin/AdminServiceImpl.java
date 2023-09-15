package com.easymeal.easymealgdc.admin;

import com.easymeal.easymealgdc.*;
import com.easymeal.easymealgdc.admin.departments.Departments;
import com.easymeal.easymealgdc.admin.departments.DepartmentsRepository;
import com.easymeal.easymealgdc.admin.positions.Positions;
import com.easymeal.easymealgdc.admin.positions.PositionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final FormatterHelper formatterHelper = new FormatterHelper();
    private final DepartmentsRepository departmentsRepository;
    private final PositionsRepository positionsRepository;

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
    public Results addStaff(DbAddStaff dbAddStaff) {
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
}
