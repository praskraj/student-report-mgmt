package com.api.annualreportmgmt.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.api.annualreportmgmt.entity.Staff;
import com.api.annualreportmgmt.repository.StaffRepository;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StaffService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private StaffRepository staffRepository;

    @Cacheable(value = "get_all_staffs")
    public Page<Staff> getAllStaff(PageRequest pageRequest) {
        return staffRepository.findAll(pageRequest);
    }

    @Cacheable(value = "get_staff_by_no")
    public Staff getStaffByNo(String staffNo) {
        return staffRepository.findById(staffNo)
                .orElseThrow(() -> new RuntimeException("Staff not found!"));
    }

    public void updateAttendance(String staffNo, String status) throws ParseException {
        Optional<Staff> staffOptional = staffRepository.findById(staffNo);
        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            staff.setStaffAttendance(status);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(new Date()); // Format current date
            staff.setDateOfRecord(sdf.parse(formattedDate)); // Set current date
            staffRepository.save(staff);
        } else {
            throw new RuntimeException("Staff not found!");
        }
    }

    public ResponseEntity<Resource> exportToExcel() {
        List<Staff> staffList = staffRepository.findAll();
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Staff List");

            Row headerRow = sheet.createRow(0);
            String[] columns = {"Staff No", "Name", "Department", "Phone", "Date", "Attendance"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowNum = 1;
            for (Staff staff : staffList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(staff.getStaffNo());
                row.createCell(1).setCellValue(staff.getStaffName());
                row.createCell(2).setCellValue(staff.getStaffDeptName());
                row.createCell(3).setCellValue(staff.getStaffPhNo());
                row.createCell(4).setCellValue(staff.getDateOfRecord());
                row.createCell(5).setCellValue(staff.getStaffAttendance());
            }

            workbook.write(out);
            ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=staff_list.xlsx")
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    public Staff updateStaffProgram(String staffNo, String staffProgram) {
        Optional<Staff> staffOptional = staffRepository.findById(staffNo);
        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            staff.setStaffProgram(staffProgram);
            return staffRepository.save(staff);
        }
        return null;
    }

    @Cacheable(value = "get_staff_by_date")
    public List<Staff> getStaffByDate(Date date) {
        return staffRepository.findByDate(date);
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

}
