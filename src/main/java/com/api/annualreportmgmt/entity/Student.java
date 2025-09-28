package com.api.annualreportmgmt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;

import java.util.Date;

import jakarta.persistence.Column;

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
    private Date dateOfRecord; // New Date column

    // Getters and Setters
    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public Long getRegno() {
        return regno;
    }

    public void setRegno(Long regno) {
        this.regno = regno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(Long deptcode) {
        this.deptcode = deptcode;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Long getPassedoutYear() {
        return passedoutYear;
    }

    public void setPassedoutYear(Long passedoutYear) {
        this.passedoutYear = passedoutYear;
    }
    

    public Date getDateOfRecord() { return dateOfRecord; }
    public void setDateOfRecord(Date dateOfRecord) { this.dateOfRecord = dateOfRecord; }
    
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getAttendanceStatus() { return attendanceStatus; }
    public void setAttendanceStatus(String attendanceStatus) { this.attendanceStatus = attendanceStatus; }

}

