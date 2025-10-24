package com.api.annualreportmgmt.service;

import com.api.annualreportmgmt.entity.User;
import com.api.annualreportmgmt.entity.Role;
import com.api.annualreportmgmt.repository.UserRepository;
import com.api.annualreportmgmt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        Role role = roleRepository.findByUserName(username);
        String userRole = (role != null) ? role.getUserRole() : "NORMALUSER";

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassWord())
                .roles(userRole) // ✅ Adds ROLE_ prefix automatically
                .build();
    }
}
