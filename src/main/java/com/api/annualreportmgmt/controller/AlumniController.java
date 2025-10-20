package com.api.annualreportmgmt.controller;

import java.util.List;
import java.util.Optional;

import com.api.annualreportmgmt.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private AlumniService alumniService;

    @PostMapping("/moveToAlumni/{rollno}")
    public ResponseEntity<String> moveStudentToAlumni(@PathVariable String rollno) {
        return alumniService.moveStudentToAlumni(rollno);
    }

    @GetMapping("/all")
    public List<Alumni> getAllAlumni() {
        return alumniService.getAllAlumni();
    }
}

