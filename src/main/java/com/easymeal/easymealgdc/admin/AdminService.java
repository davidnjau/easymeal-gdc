package com.easymeal.easymealgdc.admin;

import com.easymeal.easymealgdc.DbAddStaff;
import com.easymeal.easymealgdc.DbDepartment;
import com.easymeal.easymealgdc.DbPositions;
import com.easymeal.easymealgdc.Results;

public interface AdminService {

    Results addDepartment(DbDepartment dbDepartment);
    Results addPositions(DbPositions dbPositions);
    Results addStaff(DbAddStaff dbAddStaff);
    Results deactivateStaff(String staffId);
    Results getStats(int limit, int page);

}
