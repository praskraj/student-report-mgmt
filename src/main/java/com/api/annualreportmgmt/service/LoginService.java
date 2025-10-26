package com.api.annualreportmgmt.service;

import com.api.annualreportmgmt.dto.ApiResponse;
import com.api.annualreportmgmt.entity.Role;
import com.api.annualreportmgmt.entity.User;
import com.api.annualreportmgmt.repository.RoleRepository;
import com.api.annualreportmgmt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.api.annualreportmgmt.dto.RegisterDetails;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userRepository.findByUserNameAndPassWord(
                loginRequest.getUserName(),
                loginRequest.getPassWord()
        );

        if (user != null) {
            // Optionally, you can hide password before returning
            user.setPassWord(null);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    public ResponseEntity<ApiResponse> register(@RequestBody RegisterDetails registerDetails) {

        // Check if username already exists
        User existingUser = userRepository.findByUserName(registerDetails.getUserName());
        if (existingUser != null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse("FAILURE", "Username already exists!"));
        }

        // Check if email already exists
        User existingEmail = userRepository.findByUserEmail(registerDetails.getUserEmail());
        if (existingEmail != null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse("FAILURE", "Email already registered!"));
        }

        // Create new User
        User newUser = new User();
        newUser.setUserName(registerDetails.getUserName());
        newUser.setPassWord(registerDetails.getPassWord());
        newUser.setFirstName(registerDetails.getFirstName());
        newUser.setLastName(registerDetails.getLastName());
        newUser.setUserEmail(registerDetails.getUserEmail());
        userRepository.save(newUser);

        // Validate role
        if (registerDetails.getUserRole() == null || registerDetails.getUserRole().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse("FAILURE", "User role is required!"));
        }

        // Create corresponding Role
        Role role = new Role();
        role.setUserName(registerDetails.getUserName());
        role.setUserRole(registerDetails.getUserRole());
        roleRepository.save(role);

        return ResponseEntity.ok(
                new ApiResponse("SUCCESS", "User registered successfully with role: " + registerDetails.getUserRole())
        );
    }

}
