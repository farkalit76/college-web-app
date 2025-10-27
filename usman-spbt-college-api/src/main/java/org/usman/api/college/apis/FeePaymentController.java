package org.usman.api.college.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.api.college.repository.entities.FeePayment;
import org.usman.api.college.services.FeePaymentServiceImpl;

/**
 * Implementation Note : This class is an....
 *
 * @author UsmanFarkalit
 * @date 10-07-2025
 * @since 1.0
 */

@Slf4j
@RestController
@RequestMapping("/v1/fee/payment")
@Tag( name= "Fees Payment API management", description = "Fee Payment application")
public class FeePaymentController {

    @Autowired
    private FeePaymentServiceImpl paymentService;

    @PostMapping("/create")
    @Operation(description = "Create Fee Payment of an student")
    public ResponseEntity<?> createFeePayment(@RequestBody FeePayment payment) {
        return ResponseEntity.ok(paymentService.createFeePayment(payment));
    }

    @GetMapping("/fetch/all")
    @Operation(description = "Get all Fee Payment of students")
    public ResponseEntity<?> findAllFeePayments() {
        return ResponseEntity.ok(paymentService.getAllFeePayments());
    }

    @GetMapping("/fetch/{id}")
    @Operation(description = "Get Fee Payment of an student by Payment Reference Id")
    public ResponseEntity<?> getFeePaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getFeePaymentById(id));
    }

    @GetMapping("/fetch/student/{studentId}")
    @Operation(description = "Get Fee Payment of an student")
    public ResponseEntity<?> getFeePaymentsByStudentId(@PathVariable String studentId) {
        return null; //ResponseEntity.ok(paymentService.getPaymentsByStudentId(studentId));
    }

    @PutMapping("/update/{id}")
    @Operation(description = "Update Fee Payment of students")
    public ResponseEntity<?> updateFeePayment(@PathVariable Long id, @RequestBody FeePayment payment) {
        return ResponseEntity.ok(paymentService.updateFeePayment(id,payment));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete Fee Payment of students")
    public ResponseEntity<?> deleteFeePayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.deleteFeePayment(id));
    }
}



