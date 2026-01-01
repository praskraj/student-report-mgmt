package com.api.annualreportmgmt.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.api.annualreportmgmt.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.annualreportmgmt.entity.Address;
import com.api.annualreportmgmt.entity.Event;
import com.api.annualreportmgmt.entity.Student;
import com.api.annualreportmgmt.repository.AddressRepo;
import com.api.annualreportmgmt.repository.EventRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StudentInfoController {

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    public AddressRepo addressRepo;

    @Autowired
    private EventRepository eventRepository;

    // Get student details by rollno
    @GetMapping("/student/{rollno}")
    @PreAuthorize("hasAnyRole('ADMIN','NORMALUSER')")
    public ResponseEntity<Student> getStudentDetails(@PathVariable("rollno") String rollno) {
       return studentInfoService.getStudentDetails(rollno);
    }
    
    @GetMapping("/student/dept")
    @PreAuthorize("hasAnyRole('ADMIN','NORMALUSER')")
    public ResponseEntity<List<Student>> getStudentDetailsUsingDept(@RequestParam("deptcode") Long deptcode) {
        return studentInfoService.getStudentDetailsUsingDept(deptcode);
    }

    @GetMapping("/student/searchStudent")
    @PreAuthorize("hasAnyRole('ADMIN','NORMALUSER')")
    public ResponseEntity<List<Student>> searchStudent(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String rollNo,
            @RequestParam(required = false) Long deptcode,
            @RequestParam(required = false) String deptname
    ) {
        return studentInfoService.searchStudent(name, rollNo, deptcode, deptname);
    }

    
    @GetMapping("/student/address")
    @PreAuthorize("hasAnyRole('ADMIN','NORMALUSER')")
    public ResponseEntity<List<Address>> getStudentAddress(@RequestParam("rollno") String rollno) {
        return studentInfoService.getStudentAddress(rollno);
    }

    @GetMapping("/student")
    @PreAuthorize("hasAnyRole('ADMIN','NORMALUSER')")
    public Page<Student> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return studentInfoService.getAllStudents(page, size, sortBy);
    }

	
    // POST Method to create an Employee
    @PostMapping("/profiles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Student> createEmployee(@RequestBody Student employee) {
    	return studentInfoService.createEmployee(employee);
    }

    // POST Method to add an Address linked to an Employee
    @PostMapping("/addresses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
    	return studentInfoService.addAddress(address);
    }

    @PatchMapping("/{rollNo}/attendance")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Student> updateAttendance(
            @PathVariable String rollNo,
            @RequestParam String attendanceStatus,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfRecord) {

        return studentInfoService.updateAttendance(rollNo, attendanceStatus, dateOfRecord);
    }
    
    @GetMapping("/attendancestatus")
    @PreAuthorize("hasAnyRole('ADMIN','NORMALUSER')")
    public List<Student> getOnDutyStudents() {
        return studentInfoService.getOnDutyStudents();
    }
    
    // Fetch event by Roll Number
    @GetMapping("event/{rollNo}")
    @PreAuthorize("hasAnyRole('ADMIN','NORMALUSER')")
    public Optional<Event> getEventByRollNo(@PathVariable String rollNo) {
    	return studentInfoService.getEventByRollNo(rollNo);
    }
    
    // Create a new event
    @PostMapping("event/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Event createEvent(@RequestBody Event event) {
        return studentInfoService.createEvent(event);
    }
    
    // Fetch students by dateOfRecord
    @GetMapping("events/by-date")
    @PreAuthorize("hasAnyRole('ADMIN','NORMALUSER')")
    public List<Student> getStudentsByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date  date) {
        return studentInfoService.getStudentsByDate(date);
    }
    
    // Fetch student by Roll No
    @GetMapping("events/by-rollno")
    @PreAuthorize("hasAnyRole('ADMIN','NORMALUSER')")
    public Student getStudentByRollNo(@RequestParam("rollno") String rollno) {
        return studentInfoService.getStudentByRollNo(rollno);
    }
    
}
