package org.usman.api.college.apis;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.api.college.repository.entities.Course;
import org.usman.api.college.services.CourseServiceImpl;

/**
 * Implementation Note : This class is an....
 *
 * @author UsmanFarkalit
 * @date 10-07-2025
 * @since 1.0
 */

@Slf4j
@RestController
@RequestMapping("/v1/course")
@Tag( name= "Course management", description = "Course application")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @PostMapping("/create")
    @Operation(description = "Create a Course")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @GetMapping("/fetch/all")
    @Operation(description = "Get all Courses")
    public ResponseEntity<?> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PutMapping("/update/{id}")
    @Operation(description = "Update a Course")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course){
        return ResponseEntity.ok(courseService.updateCourse(id, course));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete a Course")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }
}
