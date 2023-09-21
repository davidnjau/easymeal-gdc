package com.easymeal.easymealgdc.admin;

import com.easymeal.easymealgdc.DbAddStaff;
import com.easymeal.easymealgdc.DbDepartment;
import com.easymeal.easymealgdc.DbPositions;
import com.easymeal.easymealgdc.Results;

public interface AdminService {

    Results addDepartment(DbDepartment dbDepartment);
    Results viewDepartments(int limit, int page);
    Results deleteDepartment(String departmentId);

    Results addPositions(DbPositions dbPositions);
    Results viewPositions(int limit, int page, String departmentId);

    Results addStaff(DbAddStaff dbAddStaff);
    Results deactivateStaff(String staffId);
    Results getStats(int limit, int page);
    Results viewStaff(int limit, int page);

}
