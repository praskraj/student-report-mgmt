package com.api.annualreportmgmt.dto;

public class ApiResponse {

    private String status;
    private String message;

    // Constructors
    public ApiResponse() {}

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters & Setters
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
