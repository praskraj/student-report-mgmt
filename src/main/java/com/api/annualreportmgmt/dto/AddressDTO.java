package com.api.annualreportmgmt.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String line1;
    private String line2;
    private String line3;
    private Long pincode;
    private String district;
    private String state;
    private String country;
    private String rollno;
}
