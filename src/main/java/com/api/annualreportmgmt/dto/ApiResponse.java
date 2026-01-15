package com.api.annualreportmgmt.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String status;
    private String message;

    public ApiResponse() {}

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
