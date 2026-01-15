package com.api.annualreportmgmt.service;

import com.api.annualreportmgmt.entity.Address;
import com.api.annualreportmgmt.entity.Event;
import com.api.annualreportmgmt.entity.Student;
import com.api.annualreportmgmt.repository.AddressRepo;
import com.api.annualreportmgmt.repository.EventRepository;
import com.api.annualreportmgmt.repository.StudentInfoRepo;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
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

    @Cacheable(value = "get_students_by_rollno")
    public ResponseEntity<Student> getStudentDetails(@PathVariable("rollno") String rollno) {

        Student student = studentRepository.findById(rollno).orElse(null);

        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Cacheable(value = "get_students_by_deptcode")
    public ResponseEntity<List<Student>> getStudentDetailsUsingDept(@RequestParam("deptcode") Long deptcode) {
        List<Student> student = studentRepository.findBydeptcode(deptcode);

        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Cacheable(value = "search_student")
    public ResponseEntity<List<Student>> searchStudent(String name, String rollNo, Long deptcode, String deptname) {

        List<Student> student = new ArrayList<>();
        if(name!=null && !name.isEmpty())   {
            student = studentRepository.searchStudent(name);
        } else if (rollNo!=null && !rollNo.isEmpty())   {
            student = studentRepository.findByrollno(rollNo);
        } else if (deptcode!=null && deptcode > 0) {
            student = studentRepository.findBydeptcode(deptcode);
        } else if (deptname!=null && !deptname.isEmpty()) {
            student = studentRepository.findBydeptname(deptname);
        }

        // If student is found, return details, otherwise return a 404
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Cacheable(value = "get_students_address")
    public ResponseEntity<List<Address>> getStudentAddress(String rollno) {
        List<Address> address = addressRepo.findByrollno(rollno);

        if (address != null) {
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Cacheable(value = "get_all_students")
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

    @Cacheable(value = "get_students_on_duty")
    public List<Student> getOnDutyStudents() {
        return studentRepository.findByAttendanceStatus("On Duty");
    }

    @Cacheable(value = "get_events_by_roll_no")
    public Optional<Event> getEventByRollNo(String rollNo) {
        return eventRepository.findById(rollNo);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Cacheable(value = "get_students_by_date")
    public List<Student> getStudentsByDate(Date  date) {
        return studentRepository.findByDateOfRecord(date);
    }

    @Cacheable(value = "get_students_by_roll_no")
    public Student getStudentByRollNo(String rollno) {
        return studentRepository.findByRollNo(rollno);
    }

    public void exportStudentsToExcel(HttpServletResponse response) throws IOException {

        List<Student> students = studentRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");

        // Header Row
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Roll No", "Reg No", "Name", "Dept Code", "Dept Name",
                "Academic Year", "Passed Out Year", "Age",
                "Attendance Status", "Date Of Record"
        };

        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // Data Rows
        int rowNum = 1;
        for (Student s : students) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(s.getRollno());
            row.createCell(1).setCellValue(s.getRegno());
            row.createCell(2).setCellValue(s.getName());
            row.createCell(3).setCellValue(s.getDeptcode());
            row.createCell(4).setCellValue(s.getDeptname());
            row.createCell(5).setCellValue(s.getAcademicYear());
            row.createCell(6).setCellValue(s.getPassedoutYear());
            if (s.getAge() != null) {
                row.createCell(7).setCellValue(s.getAge());
            }
            row.createCell(8).setCellValue(s.getAttendanceStatus());

            if (s.getDateOfRecord() != null) {
                row.createCell(9).setCellValue(s.getDateOfRecord());
            }
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
