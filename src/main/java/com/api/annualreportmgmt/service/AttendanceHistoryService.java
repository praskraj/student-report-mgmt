package com.api.annualreportmgmt.service;

import com.api.annualreportmgmt.entity.AttendanceHistory;
import com.api.annualreportmgmt.repository.AttendanceHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceHistoryService {

    private final AttendanceHistoryRepository repository;

    public AttendanceHistoryService(AttendanceHistoryRepository repository) {
        this.repository = repository;
    }

    public List<AttendanceHistory> getAll() {
        return repository.findAll();
    }

    public AttendanceHistory save(AttendanceHistory history) {
        return repository.save(history);
    }

    public List<AttendanceHistory> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}

