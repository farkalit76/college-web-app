package org.usman.api.college.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.usman.api.college.repository.entities.Student;
import org.usman.api.college.repository.entities.StudentAdmission;
import org.usman.api.college.repository.entities.ExamResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.usman.api.college.services.ExamResultServiceImpl;
import org.usman.api.college.services.StudentAdmissionServiceImpl;
import org.usman.api.college.services.StudentServiceImpl;

import java.util.List;

/**
 * UserController (for students)
 */
@RestController
@RequestMapping("/v1/student/user")
@Tag( name= "User management", description = "User application")
@RequiredArgsConstructor
public class StudentUserController {

    private final StudentServiceImpl studentService;
    private final StudentAdmissionServiceImpl admissionService;
    private final ExamResultServiceImpl resultService;

    @GetMapping("/students/{studentId}")
    @Operation(description = "Get student profile")
    public Student getStudentProfile(@PathVariable String studentId){
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/admissions/{studentId}")
    @Operation(description = "Get all student admission")
    public List<StudentAdmission> getStudentAdmissions(@PathVariable String studentId) {
        return admissionService.getStudentAdmissionByStudentId(studentId);
    }

    @GetMapping("/results/{studentId}")
    @Operation(description = "Get all student results")
    public List<ExamResult> getStudentResults(@PathVariable String studentId) {
        return resultService.getExamResultByStudentId(studentId);
    }
}