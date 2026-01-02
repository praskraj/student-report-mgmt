package com.api.annualreportmgmt.controller;

import com.api.annualreportmgmt.entity.Staff;
import com.api.annualreportmgmt.repository.StaffRepository;
import com.api.annualreportmgmt.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffService staffService;

    // Get all staff details
    @GetMapping("staff/all")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Staff> getAllStaff(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        return staffService.getAllStaff(PageRequest.of(page, size));
    }

    // Get staff by staff number
    @GetMapping("staff/{staffNo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Staff> getStaffByNo(@PathVariable String staffNo) {
        return ResponseEntity.ok(staffService.getStaffByNo(staffNo));
    }

    // Get staff by date of record
    @GetMapping("staff/date/{date}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Staff> getStaffByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return staffService.getStaffByDate(date);
    }

    // ✅ Create Staff
    @PostMapping("staff/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Staff createStaff(@RequestBody Staff staff) {
        return staffService.createStaff(staff);
    }

    @PutMapping("staff/updateAttendance/{staffNo}/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateAttendance(@PathVariable String staffNo, @PathVariable String status) throws ParseException {
        staffService.updateAttendance(staffNo, status);
        return ResponseEntity.ok("Attendance Updated");
    }

    @GetMapping("export")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<org.springframework.core.io.Resource> exportToExcel() {
        return staffService.exportToExcel();
    }

    @PutMapping("staff/updateProgram/{staffNo}")
    @PreAuthorize("hasRole('ADMIN')")
    public Staff updateStaffProgram(@PathVariable String staffNo, @RequestParam String staffProgram) {
        return staffService.updateStaffProgram(staffNo, staffProgram);
    }

}
