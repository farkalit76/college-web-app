package org.usman.api.college.apis;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.api.college.repository.entities.Examination;
import org.usman.api.college.services.ExaminationServiceImpl;

/**
 * Implementation Note : This class is an....
 *
 * @author UsmanFarkalit
 * @date 10-07-2025
 * @since 1.0
 */

@Slf4j
@RestController
@RequestMapping("/v1/examination")
@Tag( name= "ExamResult management", description = "Examination application")
public class ExaminationController {

    @Autowired
    private ExaminationServiceImpl examinationService;

    @PostMapping("/create")
    @Operation(description = "Create a examination")
    public ResponseEntity<?> createExamination(@RequestBody Examination examination) {
        return ResponseEntity.ok(examinationService.createExamination(examination));
    }

    @GetMapping("/fetch/all")
    @Operation(description = "Get all Examinations")
    public ResponseEntity<?> getAllExaminations(){
        return ResponseEntity.ok(examinationService.getAllExaminations());
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> getExaminationById(@PathVariable Long examId) {
        return ResponseEntity.ok(examinationService.getExaminationById(examId));
    }

    @PutMapping("/update/{id}")
    @Operation(description = "Update a Examination")
    public ResponseEntity<?> updateExamination(@PathVariable Long examId, @RequestBody Examination examination) {
        return ResponseEntity.ok(examinationService.updateExamination(examId, examination));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete a Examination")
    public ResponseEntity<?> deleteExamination(@PathVariable Long examId) {
        return ResponseEntity.ok(examinationService.deleteExamination(examId));
    }
}
