package org.usman.api.college.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.api.college.repository.entities.Semester;
import org.usman.api.college.services.SemesterServiceImpl;

/**
 * Implementation Note : This class is an....
 *
 * @author UsmanFarkalit
 * @date 10-07-2025
 * @since 1.0
 */

@Slf4j
@RestController
@RequestMapping("/v1/semester")
@Tag( name= "Semester management", description = "Semester application")
public class SemesterController {

    @Autowired
    private SemesterServiceImpl semesterService;

    @PostMapping("/create")
    @Operation(description = "Create a Semester")
    public ResponseEntity<?> createSemester(@RequestBody Semester semester) {
        return ResponseEntity.ok(semesterService.createSemester(semester));
    }

    @GetMapping("/fetch/{id}")
    @Operation(description = "Get Semester by Id")
    public ResponseEntity<?> getSemesterById(@PathVariable Long semesterId){
        return ResponseEntity.ok(semesterService.getSemesterById(semesterId));
    }

    @GetMapping("/fetch/all")
    @Operation(description = "Get all Semester")
    public ResponseEntity<?> getAllSemesters() {
        return ResponseEntity.ok(semesterService.getAllSemesters());
    }

    @PutMapping("/update/{id}")
    @Operation(description = "Update a Semester")
    public ResponseEntity<?> updateSemester(@PathVariable Long semesterId, @RequestBody Semester semester){
        return ResponseEntity.ok(semesterService.updateSemester(semesterId, semester));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete a Semester")
    public ResponseEntity<?> deleteSemester(@PathVariable Long semesterId) {
        return ResponseEntity.ok(semesterService.deleteSemester(semesterId));
    }
}
