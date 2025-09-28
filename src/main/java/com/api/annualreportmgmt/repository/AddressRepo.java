package com.api.annualreportmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.annualreportmgmt.entity.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, String> {

	public List<Address> findByrollno(String rollno);
}



