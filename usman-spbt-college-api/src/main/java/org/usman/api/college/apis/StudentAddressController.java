package org.usman.api.college.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.api.college.repository.entities.StudentAddress;
import org.usman.api.college.services.StudentAddressServiceImpl;

/**
 * Implementation Note : This class is an....
 *
 * @author UsmanFarkalit
 * @date 10-07-2025
 * @since 1.0
 */

@Slf4j
@RestController
@RequestMapping("/v1/student/address")
@Tag( name= "Student's address management", description = "Student's address application")
public class StudentAddressController {

    @Autowired
    private StudentAddressServiceImpl addressService;

    @PostMapping("/create")
    @Operation(description = "Create a student's address")
    public ResponseEntity<?> createStudentAddress(@RequestBody StudentAddress studentAddress){
        return ResponseEntity.ok(addressService.createStudentAddress(studentAddress));
    }

    @GetMapping("/fetch/{id}")
    @Operation(description = "Get student's address by Id")
    public ResponseEntity<?> getStudentAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getStudentAddressById(id));
    }

    @GetMapping("/fetch/student/{studentId}")
    @Operation(description = "Get student's address by Id")
    public ResponseEntity<?> getStudentAddressByStudentId(@PathVariable String studentId) {
        return ResponseEntity.ok(addressService.getStudentAddressByStudentId(studentId));
    }

    @GetMapping("/fetch/all")
    @Operation(description = "Get all students addresses")
    public ResponseEntity<?> getAllStudentsAddress() {
        return ResponseEntity.ok(addressService.getAllStudentAddresses());
    }

    @PutMapping("/update/{id}")
    @Operation(description = "Update student address")
    public ResponseEntity<?> updateStudentAddress(@PathVariable Long id, @RequestBody StudentAddress studentAddress){
        return ResponseEntity.ok(addressService.updateStudentAddress(id, studentAddress));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete a StudentAddress")
    public ResponseEntity<?> deleteStudentAddress(@PathVariable Long id) {
        return  ResponseEntity.ok(addressService.deleteStudentAddressById(id));
    }

}
