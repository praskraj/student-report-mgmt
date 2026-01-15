package com.api.annualreportmgmt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;


@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @Column(name = "staff_no")
    private String staffNo;

    @Column(name = "staff_name")
    private String staffName;

    @Column(name = "staff_dept_no")
    private Long staffDeptNo;

    @Column(name = "staff_dept_name")
    private String staffDeptName;

    @Column(name = "staff_attendance")
    private String staffAttendance;

    @Column(name = "staff_ph_no")
    private Long staffPhNo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_record")
    private Date dateOfRecord;
    
    @Column(name = "staffProgram")
    private String staffProgram;

    public String getStaffProgram() {
		return staffProgram;
	}

	public void setStaffProgram(String staffProgram) {
		this.staffProgram = staffProgram;
	}

	public Date getDateOfRecord() {
		return dateOfRecord;
	}

	public void setDateOfRecord(Date dateOfRecord) {
		this.dateOfRecord = dateOfRecord;
	}

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getStaffDeptNo() {
        return staffDeptNo;
    }

    public void setStaffDeptNo(Long staffDeptNo) {
        this.staffDeptNo = staffDeptNo;
    }

    public String getStaffDeptName() {
        return staffDeptName;
    }

    public void setStaffDeptName(String staffDeptName) {
        this.staffDeptName = staffDeptName;
    }

    public String getStaffAttendance() {
        return staffAttendance;
    }

    public void setStaffAttendance(String staffAttendance) {
        this.staffAttendance = staffAttendance;
    }

    public Long getStaffPhNo() {
        return staffPhNo;
    }

    public void setStaffPhNo(Long staffPhNo) {
        this.staffPhNo = staffPhNo;
    }
}
