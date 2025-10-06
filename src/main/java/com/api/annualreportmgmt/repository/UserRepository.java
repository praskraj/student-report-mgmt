package com.api.annualreportmgmt.repository;

import com.api.annualreportmgmt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserNameAndPassWord(String userName, String passWord);
}
