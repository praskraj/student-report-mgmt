package com.api.annualreportmgmt.controller;


import org.springframework.web.bind.annotation.*;

import com.api.annualreportmgmt.entity.AttendanceHistory;
import com.api.annualreportmgmt.repository.AttendanceHistoryRepository;

import java.util.List;

@RestController
@RequestMapping("/attendance-history")
@CrossOrigin
public class AttendanceHistoryController {

    private final AttendanceHistoryRepository repository;

    public AttendanceHistoryController(AttendanceHistoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<AttendanceHistory> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public AttendanceHistory save(@RequestBody AttendanceHistory history) {
        return repository.save(history);
    }

    @GetMapping("/search")
    public List<AttendanceHistory> searchByName(@RequestParam String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}

