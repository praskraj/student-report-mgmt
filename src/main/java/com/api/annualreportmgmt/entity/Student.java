package com.api.annualreportmgmt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "rollno")
    private String rollno;

    @Column(name = "regno", unique = true)
    private Long regno;

    @Column(name = "name")
    private String name;

    @Column(name = "deptcode")
    private Long deptcode;

    @Column(name = "deptname")
    private String deptname;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "passedout_year")
    private Long passedoutYear;
    
    @Column(name = "age")
    private Integer age;  // New column

    @Column(name = "attendance_status")
    private String attendanceStatus; // New column (e.g., Present/Absent)
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_record")
    private Date dateOfRecord;
}

