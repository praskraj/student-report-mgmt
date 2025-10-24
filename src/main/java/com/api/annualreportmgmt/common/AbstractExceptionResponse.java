package com.api.annualreportmgmt.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractExceptionResponse {
    public ResponseEntity<Object> setAuthentication(HttpStatus status,String message){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
