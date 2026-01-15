package com.api.annualreportmgmt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
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
}
