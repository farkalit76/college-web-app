package org.usman.api.college.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.api.college.repository.entities.ExamResult;
import org.usman.api.college.services.ExamResultServiceImpl;

/**
 * Implementation Note : This class is an....
 *
 * @author UsmanFarkalit
 * @date 10-07-2025
 * @since 1.0
 */

@Slf4j
@RestController
@RequestMapping("/v1/exam/result")
@Tag( name= "Student's ExamResult management", description = "Student's ExamResult application")
public class ExamResultController {

    @Autowired
    private ExamResultServiceImpl examResultService;

    @PostMapping("/create")
    @Operation(description = "Create an ExamResult")
    public ResponseEntity<?> createExamResult(@RequestBody ExamResult examResult){
        return ResponseEntity.ok(examResultService.createExamResult(examResult));
    }

    @GetMapping("/fetch/{id}")
    @Operation(description = "Get ExamResult by Id")
    public ResponseEntity<?> getExamResultById(@PathVariable Long resultId) {
        return ResponseEntity.ok(examResultService.getExamResultById(resultId));
    }

    @GetMapping("/fetch/all")
    @Operation(description = "Get all ExamResults")
    public ResponseEntity<?> getAllExamResults() {
        return ResponseEntity.ok(examResultService.getAllExamResults());
    }

    @PutMapping("/update/{id}")
    @Operation(description = "Update a ExamResult")
    public ResponseEntity<?> updateExamResult(@PathVariable Long resultId, @RequestBody ExamResult examResult) {
        return ResponseEntity.ok(examResultService.updateExamResult(resultId, examResult));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete a ExamResult")
    public ResponseEntity<?> deleteExamResult(@PathVariable Long resultId) {
        return ResponseEntity.ok(examResultService.deleteExamResult(resultId));
    }
}
