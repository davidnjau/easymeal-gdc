package com.easymeal.easymealgdc.admin;

import com.easymeal.easymealgdc.DbAddStaff;
import com.easymeal.easymealgdc.DbDepartment;
import com.easymeal.easymealgdc.DbPositions;
import com.easymeal.easymealgdc.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    @Override
    public Results addDepartment(DbDepartment dbDepartment) {
        return null;
    }

    @Override
    public Results addPositions(DbPositions dbPositions) {
        return null;
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
