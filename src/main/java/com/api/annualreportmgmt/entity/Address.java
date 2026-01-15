package com.api.annualreportmgmt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "line1")
    private String line1;

    @Column(name = "line2")
    private String line2;

    @Column(name = "line3")
    private String line3;

    @Column(name = "pincode")
    private Long pincode;

    @Column(name = "district")
    private String district;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;
    
    @Column(name = "rollno")
    private String rollno;
    
}

