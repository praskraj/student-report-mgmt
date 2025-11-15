package com.api.annualreportmgmt.service;

import com.api.annualreportmgmt.entity.Address;
import com.api.annualreportmgmt.entity.Event;
import com.api.annualreportmgmt.entity.Student;
import com.api.annualreportmgmt.repository.AddressRepo;
import com.api.annualreportmgmt.repository.EventRepository;
import com.api.annualreportmgmt.repository.StudentInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentInfoService {

    @Autowired
    private StudentInfoRepo studentRepository;

    @Autowired
    public AddressRepo addressRepo;

    @Autowired
    private EventRepository eventRepository;

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

    public ResponseEntity<List<Student>> searchStudent(String name) {
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

    public ResponseEntity<List<Address>> getStudentAddress(String rollno) {
        // Fetch student from the repository
        List<Address> address = addressRepo.findByrollno(rollno);

        // If student is found, return details, otherwise return a 404
        if (address != null) {
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Page<Student> getAllStudents(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return studentRepository.findAll(pageable);
    }

    public ResponseEntity<Student> createEmployee(Student employee) {
        Student savedEmployee = studentRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    public ResponseEntity<Address> addAddress(Address address) {
        Address savedEmployee = addressRepo.save(address);
        return ResponseEntity.ok(savedEmployee);
    }

    public ResponseEntity<Student> updateAttendance(String rollNo, String attendanceStatus, Date dateOfRecord) {

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

    public List<Student> getOnDutyStudents() {
        return studentRepository.findByAttendanceStatus("On Duty");
    }

    public Optional<Event> getEventByRollNo(String rollNo) {
        return eventRepository.findById(rollNo);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Student> getStudentsByDate(Date  date) {
        return studentRepository.findByDateOfRecord(date);
    }

    public Student getStudentByRollNo(String rollno) {
        return studentRepository.findByRollNo(rollno);
    }

}
