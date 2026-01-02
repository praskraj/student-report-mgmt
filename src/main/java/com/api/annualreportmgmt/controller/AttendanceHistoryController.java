package com.api.annualreportmgmt.controller;


import com.api.annualreportmgmt.service.AttendanceHistoryService;
import org.springframework.security.access.prepost.PreAuthorize;

import com.api.annualreportmgmt.entity.AttendanceHistory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attendance-history")
@CrossOrigin
public class AttendanceHistoryController {

    private final AttendanceHistoryService attendanceHistoryService;

    public AttendanceHistoryController(AttendanceHistoryService attendanceHistoryService) {
        this.attendanceHistoryService = attendanceHistoryService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','NORMALUSER')")
    public List<AttendanceHistory> getAll() {
        return attendanceHistoryService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public AttendanceHistory save(@RequestBody AttendanceHistory history) {
        return attendanceHistoryService.save(history);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','NORMALUSER')")
    public List<AttendanceHistory> searchByName(@RequestParam String name) {
        return attendanceHistoryService.searchByName(name);
    }
}

