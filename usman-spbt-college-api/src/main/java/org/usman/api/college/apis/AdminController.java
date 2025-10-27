package org.usman.api.college.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.usman.api.college.repository.entities.Student;
import org.usman.api.college.repository.entities.Course;
import org.usman.api.college.services.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.usman.api.college.services.StudentServiceImpl;

import java.util.List;

/**
 * AdminController (for college staff)
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin")
@Tag( name= "User's Admin management", description = "User's Admin application")
@RequiredArgsConstructor
public class AdminController {

    private final StudentServiceImpl studentService;
    private final CourseServiceImpl courseService;

    @PostMapping("/students")
    @Operation(description = "Create a Course")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/students/{id}")
    @Operation(description = "Create a Student")
    public Student updateStudent(@PathVariable String studentId, @RequestBody Student student) {
        return studentService.updateStudent(studentId, student);
    }

    @DeleteMapping("/students/{id}")
    @Operation(description = "Delete student by Id")
    public void deleteStudent(@PathVariable String studentId) {
        studentService.deleteStudent(studentId);
    }

    @GetMapping("/students")
    @Operation(description = "Get all Students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }


    @PostMapping("/courses")
    @Operation(description = "Create a Course")
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @GetMapping("/courses")
    @Operation(description = "Get all Course")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
}
