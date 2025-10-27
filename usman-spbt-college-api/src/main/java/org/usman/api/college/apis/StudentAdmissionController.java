package org.usman.api.college.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.api.college.repository.entities.StudentAdmission;
import org.usman.api.college.services.StudentAdmissionServiceImpl;

/**
 * Implementation Note : This class is an....
 *
 * @author UsmanFarkalit
 * @date 10-07-2025
 * @since 1.0
 */

@Slf4j
@RestController
@RequestMapping("/v1/student/admission")
@Tag( name= "StudentAdmission management", description = "StudentAdmission application")
public class StudentAdmissionController {

    @Autowired
    private StudentAdmissionServiceImpl admissionService;

    @PostMapping("/create")
    @Operation(description = "Create an admission")
    public ResponseEntity<?> createStudentAdmission(@RequestBody StudentAdmission studentAdmission) {
        return ResponseEntity.ok(admissionService.createStudentAdmission(studentAdmission));
    }

    @GetMapping("/fetch/{id}")
    @Operation(description = "Get admission by Id")
    public ResponseEntity<?> getStudentAdmissionById(@PathVariable String admissionId){
        return ResponseEntity.ok(admissionService.getStudentAdmissionById(admissionId));
    }

    @GetMapping("/fetch/all")
    @Operation(description = "Get all admissions")
    public ResponseEntity<?> getAllStudentAdmissions() {
        return ResponseEntity.ok(admissionService.getAllStudentAdmission());
    }

    @PutMapping("/update/{id}")
    @Operation(description = "Update a admission")
    public ResponseEntity<?> updateStudentAdmission(@PathVariable String admissionId, @RequestBody StudentAdmission studentAdmission) {
        return ResponseEntity.ok(admissionService.updateStudentAdmission(admissionId, studentAdmission));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete a StudentAdmission")
    public ResponseEntity<?> deleteStudentAdmission(@PathVariable String admissionId) {
        return ResponseEntity.ok(admissionService.deleteStudentAdmission(admissionId));
    }
}
