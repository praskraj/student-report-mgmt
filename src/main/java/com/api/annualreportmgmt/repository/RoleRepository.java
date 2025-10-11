package com.api.annualreportmgmt.repository;

import com.api.annualreportmgmt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByUserName(String userName);
}
