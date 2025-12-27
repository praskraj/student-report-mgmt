package com.api.annualreportmgmt.service;

import com.api.annualreportmgmt.entity.Alumni;
import com.api.annualreportmgmt.entity.Student;
import com.api.annualreportmgmt.repository.AlumniRepository;
import com.api.annualreportmgmt.repository.StudentInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class AlumniService {

    @Autowired
    private AlumniRepository alumniRepository;

    @Autowired
    private StudentInfoRepo studentRepository;

    public ResponseEntity<String> moveStudentToAlumni(String rollno) {
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


    @Cacheable(value = "get_all_alumni")
    public List<Alumni> getAllAlumni() {
        return alumniRepository.findAll();
    }
}
