package com.api.annualreportmgmt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "alumni")
public class Alumni {

    @Id
    private String rollno;
    private Long regno;
    private String name;
    private int age;
    private Long deptno;
    private String deptname;
    private Long passedoutyear;
    private String address;
    
}

