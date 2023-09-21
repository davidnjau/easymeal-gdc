package com.easymeal.easymealgdc.admin.positions;

import com.easymeal.easymealgdc.admin.departments.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionsRepository extends JpaRepository<Positions, String> {
    Boolean existsByName(String name);
    List<Positions> findByDepartment(Departments departments);
}
