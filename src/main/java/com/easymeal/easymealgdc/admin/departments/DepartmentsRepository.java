package com.easymeal.easymealgdc.admin.departments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepository extends JpaRepository<Departments, String> {

    Boolean existsByName(String name);

}
