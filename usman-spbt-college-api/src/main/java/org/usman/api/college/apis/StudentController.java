package org.usman.api.college.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.api.college.repository.entities.Student;
import org.usman.api.college.services.StudentServiceImpl;

/**
 * Implementation Note : This class is an....
 *
 * @author UsmanFarkalit
 * @date 10-07-2025
 * @since 1.0
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/student")
@Tag( name= "Student management", description = "Student application")
@CrossOrigin(origins = "http://localhost:4200")
@SecurityRequirement(name = "X-Auth-Key")  //  tells Swagger this API needs the header
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/create")
    @Operation(description = "Create a student")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping("/fetch/{studentId}")
    @Operation(description = "Get student by Id")
    public ResponseEntity<?> getStudentById(@PathVariable String studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @GetMapping("/fetch/all")
    @Operation(description = "Get all students")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/update/{studentId}")
    @Operation(description = "Update a student")
    public ResponseEntity<?> updateStudent(@PathVariable String studentId, @RequestBody Student student){
        return ResponseEntity.ok(studentService.updateStudent(studentId, student));
    }

    @DeleteMapping("/delete/{studentId}")
    @Operation(description = "Delete a Student")
    public ResponseEntity<?> deleteStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(studentService.deleteStudent(studentId));
    }
}
