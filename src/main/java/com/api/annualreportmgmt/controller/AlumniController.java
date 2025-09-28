package com.api.annualreportmgmt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.annualreportmgmt.entity.Alumni;
import com.api.annualreportmgmt.entity.Student;
import com.api.annualreportmgmt.repository.AlumniRepository;
import com.api.annualreportmgmt.repository.StudentInfoRepo;

@RestController
@RequestMapping("/alumni")
@CrossOrigin
public class AlumniController {

    @Autowired
    private AlumniRepository alumniRepository;

    @Autowired
    private StudentInfoRepo studentRepository;

    @PostMapping("/moveToAlumni/{rollno}")
    public ResponseEntity<String> moveStudentToAlumni(@PathVariable String rollno) {
        Optional<Student> studentOpt = studentRepository.findById(rollno);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();

            // Create alumni
            Alumni alumni = new Alumni();
            alumni.setRollno(student.getRollno());
            alumni.setRegno(student.getRegno());
            alumni.setName(student.getName());
            alumni.setAge(student.getAge());
            alumni.setDeptno(student.getDeptcode());
            alumni.setDeptname(student.getDeptname());
            alumni.setPassedoutyear(student.getPassedoutYear());

            alumniRepository.save(alumni);
            studentRepository.deleteById(rollno); // delete student from student table

            return ResponseEntity.ok("Moved to Alumni and removed from Student table");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }


    @GetMapping("/all")
    public List<Alumni> getAllAlumni() {
        return alumniRepository.findAll();
    }
}

