package com.api.annualreportmgmt.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String passWord;
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userRole;
}
