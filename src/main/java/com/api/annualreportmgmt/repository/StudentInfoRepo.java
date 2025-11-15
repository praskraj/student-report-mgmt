package com.api.annualreportmgmt.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.annualreportmgmt.entity.Student;

@Repository
public interface StudentInfoRepo extends JpaRepository<Student, String> {

	public List<Student> findBydeptcode(Long deptCode);
	
	@Query(value="select regno,rollno,age,name,deptcode,deptname,academic_year,passedout_year,attendance_status,date_of_record from student where lower(name) like %:name%",nativeQuery = true)
	public List<Student> searchStudent(@Param("name") String name);
	
	List<Student> findByAttendanceStatus(String attendanceStatus);
	
    @Query("SELECT s FROM Student s WHERE s.dateOfRecord = :date and s.attendanceStatus = 'On Duty'")
    List<Student> findByDateOfRecord(@Param("date") Date date);
    
    @Query("SELECT s FROM Student s WHERE s.rollno = :rollno")
    Student findByRollNo(@Param("rollno") String rollno);

    public List<Student> findByrollno(@Param("rollno") String rollno);

    public List<Student> findByname(@Param("name") String name);

    public List<Student> findBydeptname(@Param("deptname") String deptname);

	
}
