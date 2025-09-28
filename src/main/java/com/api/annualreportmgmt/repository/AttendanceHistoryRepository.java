package com.api.annualreportmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.annualreportmgmt.entity.AttendanceHistory;

import java.util.List;

public interface AttendanceHistoryRepository extends JpaRepository<AttendanceHistory, Long> {
    List<AttendanceHistory> findByNameContainingIgnoreCase(String name);
}
