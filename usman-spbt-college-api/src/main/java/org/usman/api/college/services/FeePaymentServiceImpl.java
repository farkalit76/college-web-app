package org.usman.api.college.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.usman.api.college.repository.StudentAdmissionRepository;
import org.usman.api.college.repository.FeePaymentRepository;
import org.usman.api.college.repository.entities.FeePayment;

import java.util.List;

/**
 * Implementation Note : This class is an....
 *
 * @author UsmanFarkalit
 * @date 10-07-2025
 * @since 1.0
 */

@Slf4j
@Service
public class FeePaymentServiceImpl {

    @Autowired
    private FeePaymentRepository feePaymentRepository;

    @Autowired
    private StudentAdmissionRepository studentAdmissionRepository;

    public List<FeePayment> getAllPayments() {
        return feePaymentRepository.findAll();
    }

    public FeePayment getPaymentById(Long id) {
        return feePaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public FeePayment createPayment(FeePayment payment) {
        try {
            log.info("Start createPayment :{}", payment);
            //Save the payment and update the Enrollment status
            //payment.setReferenceNumber(UUID.randomUUID().toString());
            log.info("Start saving payment for student ID:{}", payment.getPaymentId());
            FeePayment feePayment = feePaymentRepository.save(payment);
            log.info("Payment  saved for payment ID:{}", payment.getPaymentId());
            //List<Enrollment> enrollmentList = studentAdmissionRepository.findByStudentIdAndCourseId(payment.getStudentId(), payment.getCourseId());
            //log.info("Payment enrollmentList by ID:{}", enrollmentList);

//            if (enrollmentList.isEmpty()) {
//                log.info("Student Enrollment not found for studentId:{} and courseId :{}", payment.getStudentId(), payment.getCourseId());
//                throw new RuntimeException("Student Enrollment not found for studentId:{}" + payment.getStudentId());
//            } else {
//                Enrollment enrollment = enrollmentList.get(0);
//                log.info("enrollmentsByStudentId :{}", enrollment);
//                enrollment.setIsPaid(true);
//                enrollment.setPaymentId(feePayment.getPaymentId());
//                enrollment.setIsActive(true);
//                studentAdmissionRepository.save(enrollment);
//                log.info("Enrollment for StudentId :{} and Payment Id:{} activated", feePayment.getStudentId(), feePayment.getPaymentId());
//            }
            return feePayment;
        } catch (Exception e) {
            log.error("Payment creation failed with studentId: {}", payment.getPaymentId());
            throw new RuntimeException("Payment creation failed with studentId: " + payment.getPaymentId());
        }
    }

    public FeePayment updatePayment(Long id, FeePayment updatedPayment) {
        return feePaymentRepository.findById(id).map(payment -> {
            //payment.setStudentId(updatedPayment.getStudentId());
            //payment.setAcademicYear(updatedPayment.getAcademicYear());
            //payment.setFeePaid(updatedPayment.getFeePaid());
            //payment.setPenaltyPaid(updatedPayment.getPenaltyPaid());
            payment.setPaymentDate(updatedPayment.getPaymentDate());
            payment.setPaymentMode(updatedPayment.getPaymentMode());
            //payment.setReferenceNumber(updatedPayment.getReferenceNumber());
            //payment.setModifiedBy(updatedPayment.getModifiedBy());

            return feePaymentRepository.save(payment);
        }).orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    public String deletePayment(Long id) {
        feePaymentRepository.deleteById(id);
        return "DELETED";
    }

    public FeePayment createFeePayment(FeePayment feePayment) {
        return feePaymentRepository.save(feePayment);
    }

    public FeePayment updateFeePayment(Long paymentId, FeePayment feePayment) {
        FeePayment existing = feePaymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("fee payment not found for : " + paymentId));
        existing.setAdmissionId(feePayment.getAdmissionId());
        existing.setInstallmentNo(feePayment.getInstallmentNo());
        existing.setAmountPaid(feePayment.getAmountPaid());
        existing.setPaymentMode(feePayment.getPaymentMode());
        return feePaymentRepository.save(existing);
    }

    public int deleteFeePayment(Long paymentId) {
        feePaymentRepository.deleteById(paymentId);
        return 1;
    }

    public FeePayment getFeePaymentById(Long paymentId) {
        return feePaymentRepository.findById(paymentId)
                .orElseThrow( () -> new RuntimeException("Fee payment not found for:"+paymentId));
    }

    public List<FeePayment> getAllFeePayments() {
        return feePaymentRepository.findAll();
    }
}
