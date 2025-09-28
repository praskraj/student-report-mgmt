package com.api.annualreportmgmt.repository;

import com.api.annualreportmgmt.entity.Staff;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StaffRepository extends JpaRepository<Staff, String> {
	
    // Find staff by date of record
    @Query("SELECT s FROM Staff s WHERE s.dateOfRecord = :date")
    List<Staff> findByDate(@Param("date") Date date);
	
}
