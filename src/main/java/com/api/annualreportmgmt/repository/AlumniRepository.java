package com.api.annualreportmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.annualreportmgmt.entity.Alumni;

public interface AlumniRepository extends JpaRepository<Alumni, String> {
}

