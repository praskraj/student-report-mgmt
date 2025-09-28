package com.api.annualreportmgmt.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.annualreportmgmt.entity.Address;
import com.api.annualreportmgmt.entity.Event;
import com.api.annualreportmgmt.entity.Staff;
import com.api.annualreportmgmt.entity.Student;
import com.api.annualreportmgmt.repository.AddressRepo;
import com.api.annualreportmgmt.repository.EventRepository;
import com.api.annualreportmgmt.repository.StaffRepository;
import com.api.annualreportmgmt.repository.StudentInfoRepo;
import com.api.annualreportmgmt.service.StaffService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StudentInfoController {

    @Autowired
    private StudentInfoRepo studentRepository;
    
    @Autowired
    public AddressRepo addressRepo;
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private StaffService staffService;

    // Get student details by rollno
    @GetMapping("/student/{rollno}")
    public ResponseEntity<Student> getStudentDetails(@PathVariable("rollno") String rollno) {
        // Fetch student from the repository
        Student student = studentRepository.findById(rollno).orElse(null);

        // If student is found, return details, otherwise return a 404
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/student/dept")
    public ResponseEntity<List<Student>> getStudentDetailsUsingDept(@RequestParam("deptcode") Long deptcode) {
        // Fetch student from the repository
        List<Student> student = studentRepository.findBydeptcode(deptcode);
        
        // If student is found, return details, otherwise return a 404
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/student/searchStudent")
    public ResponseEntity<List<Student>> searchStudent(@RequestParam("name") String name) {
        // Fetch student from the repository
    	
    	name = (name!=null?name.toLowerCase():""); 
    	
        List<Student> student = studentRepository.searchStudent(name);
        
        // If student is found, return details, otherwise return a 404
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/student/address")
    public ResponseEntity<List<Address>> getStudentAddress(@RequestParam("rollno") String rollno) {
        // Fetch student from the repository
        List<Address> address = addressRepo.findByrollno(rollno);
        
        // If student is found, return details, otherwise return a 404
        if (address != null) {
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/student/")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
	
    // POST Method to create an Employee
    @PostMapping("/profiles")
    public ResponseEntity<Student> createEmployee(@RequestBody Student employee) {
    	Student savedEmployee = studentRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    // POST Method to add an Address linked to an Employee
    @PostMapping("/addresses")
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
    	Address savedEmployee = addressRepo.save(address);
        return ResponseEntity.ok(savedEmployee);
    }
    
    
    @PatchMapping("/{rollNo}/attendance")
    public ResponseEntity<Student> updateAttendance(
            @PathVariable String rollNo,
            @RequestParam String attendanceStatus,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfRecord) {

        Optional<Student> optionalStudent = studentRepository.findById(rollNo);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setAttendanceStatus(attendanceStatus);
            student.setDateOfRecord(dateOfRecord != null ? dateOfRecord : new Date()); // Default to today if not provided
            studentRepository.save(student);
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/attendancestatus")
    public List<Student> getOnDutyStudents() {
        return studentRepository.findByAttendanceStatus("On Duty");
    }
    
    // Fetch event by Roll Number
    @GetMapping("event/{rollNo}")
    public Optional<Event> getEventByRollNo(@PathVariable String rollNo) {
    	return eventRepository.findById(rollNo);
    }
    
    // Create a new event
    @PostMapping("event/create")
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }
    
    // Fetch students by dateOfRecord
    @GetMapping("events/by-date")
    public List<Student> getStudentsByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date  date) {
        return studentRepository.findByDateOfRecord(date);
    }
    
 // Fetch student by Roll No
    @GetMapping("events/by-rollno")
    public Student getStudentByRollNo(@RequestParam("rollno") String rollno) {
        return studentRepository.findByRollNo(rollno); 
    }
    

    // Get all staff details
    @GetMapping("staff/all")
    public Page<Staff> getAllStaff(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        return staffService.getAllStaff(PageRequest.of(page, size));
    }

    // Get staff by staff number
    @GetMapping("staff/{staffNo}")
    public ResponseEntity<Staff> getStaffByNo(@PathVariable String staffNo) {
        return ResponseEntity.ok(staffService.getStaffByNo(staffNo));
    }
    
    // Get staff by date of record
    @GetMapping("staff/date/{date}")
    public List<Staff> getStaffByDate(
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return staffRepository.findByDate(date);
    }
    
    // ✅ Create Staff
    @PostMapping("staff/create")
    public Staff createStaff(@RequestBody Staff staff) {
        return staffRepository.save(staff);
    }
    
    @PutMapping("staff/updateAttendance/{staffNo}/{status}")
    public ResponseEntity<String> updateAttendance(@PathVariable String staffNo, @PathVariable String status) throws ParseException {
        staffService.updateAttendance(staffNo, status);
        return ResponseEntity.ok("Attendance Updated");
    } 

    @GetMapping("/export")
    public ResponseEntity<org.springframework.core.io.Resource> exportToExcel() {
        return staffService.exportToExcel();
    }
    
    @PutMapping("staff/updateProgram/{staffNo}")
    public Staff updateStaffProgram(@PathVariable String staffNo, @RequestParam String staffProgram) {
        return staffService.updateStaffProgram(staffNo, staffProgram);
    }
    
}
