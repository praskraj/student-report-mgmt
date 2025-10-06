package com.api.annualreportmgmt.controller;


import com.api.annualreportmgmt.entity.User;
import com.api.annualreportmgmt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        User user = userRepository.findByUserNameAndPassWord(
                loginRequest.getUserName(),
                loginRequest.getPassWord()
        );

        if (user != null) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User newUser) {
        // Check if username already exists
        User existingUser = userRepository.findByUserNameAndPassWord(newUser.getUserName(), newUser.getPassWord());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("User already exists!");
        }

        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully!");
    }
}

