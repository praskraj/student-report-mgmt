package com.api.annualreportmgmt.controller;

import com.api.annualreportmgmt.dto.ApiResponse;
import com.api.annualreportmgmt.dto.RegisterDetails;
import com.api.annualreportmgmt.entity.User;
import com.api.annualreportmgmt.service.LoginService;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        return loginService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterDetails newUser) {
        return loginService.register(newUser);
    }
}

