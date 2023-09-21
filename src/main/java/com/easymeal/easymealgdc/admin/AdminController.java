package com.easymeal.easymealgdc.admin;

import com.easymeal.easymealgdc.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api/v1/")
public class AdminController {

    private final FormatterHelper formatterHelper = new FormatterHelper();

    private final AdminService adminService;

    /**
     * Departments and positions
     **/
    @PostMapping(path = "add-department")
    public ResponseEntity<?> addDepartment(@RequestBody DbDepartment dbDepartment){
        Results results = adminService.addDepartment(dbDepartment);
        return formatterHelper.getResponseEntity(results);
    }
    @RequestMapping(value = "view-department", method = {RequestMethod.GET})
    public ResponseEntity<?> viewDepartment(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int page
    ){
        Results results = adminService.viewDepartments(limit, page);
        return formatterHelper.getResponseEntity(results);
    }
    @RequestMapping(value = "delete-department", method = {RequestMethod.DELETE})
    public ResponseEntity<?> deleteDepartment(
            @RequestParam(defaultValue = "") String departmentId
    ){
        Results results = adminService.deleteDepartment(departmentId);
        return formatterHelper.getResponseEntity(results);
    }

    @PostMapping(path = "add-position")
    public ResponseEntity<?> addPositions(@RequestBody DbPositions dbPositions){
        Results results = adminService.addPositions(dbPositions);
        return formatterHelper.getResponseEntity(results);
    }
    @RequestMapping(value = "view-position/{departmentId}", method = {RequestMethod.GET})
    public ResponseEntity<?> viewPositions(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int page,
            @PathVariable("departmentId") String departmentId

    ){
        Results results = adminService.viewPositions(limit, page, departmentId);
        return formatterHelper.getResponseEntity(results);
    }
    @RequestMapping(value = "delete-position", method = {RequestMethod.DELETE})
    public ResponseEntity<?> deletePosition(
            @RequestParam(defaultValue = "") String positionId
    ){
        Results results = adminService.deletePosition(positionId);
        return formatterHelper.getResponseEntity(results);
    }

    /**
     * Staff
     **/
    @PostMapping(path = "add-staff")
    public ResponseEntity<?> addStaff(@RequestBody DbAddStaff dbAddStaff){
        Results results = adminService.addStaff(dbAddStaff);
        return formatterHelper.getResponseEntity(results);
    }
    @RequestMapping(value = "view-staff", method = {RequestMethod.GET})
    public ResponseEntity<?> viewStaff(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int page
    ){
        Results results = adminService.viewStaff(limit, page);
        return formatterHelper.getResponseEntity(results);
    }
    @DeleteMapping(path = "deactivate-staff")
    public ResponseEntity<?> deactivateStaff(String staffId){
        Results results = adminService.deactivateStaff(staffId);
        return formatterHelper.getResponseEntity(results);
    }
    @RequestMapping(value = "statistics", method = {RequestMethod.GET})
    public ResponseEntity<?> getStats(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int page
    ){
        Results results = adminService.getStats(limit, page);
        return formatterHelper.getResponseEntity(results);
    }

}
