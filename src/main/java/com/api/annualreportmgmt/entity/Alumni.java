package com.api.annualreportmgmt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
	
	public Long getDeptno() {
		return deptno;
	}
	public void setDeptno(Long deptno) {
		this.deptno = deptno;
	}
	public Long getPassedoutyear() {
		return passedoutyear;
	}
	public void setPassedoutyear(Long passedoutyear) {
		this.passedoutyear = passedoutyear;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

    
}

