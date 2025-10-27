package org.usman.api.college.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usman.api.college.repository.StudentAdmissionRepository;
import org.usman.api.college.repository.entities.StudentAdmission;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentAdmissionServiceImpl {

    private final StudentAdmissionRepository admissionRepository;

    public StudentAdmission createStudentAdmission(StudentAdmission admission) {
        return admissionRepository.save(admission);
    }

    public StudentAdmission updateStudentAdmission(String admissionId, StudentAdmission admission) {
        //return null;
        StudentAdmission existing = admissionRepository.findById(admissionId)
                .orElseThrow(() -> new RuntimeException("Student admission not found for: " + admissionId));
        existing.setStudentId(admission.getStudentId());
        existing.setCourseCode(admission.getCourseCode());
        existing.setAdmissionYear(admission.getAdmissionYear());
        existing.setStatus(admission.getStatus());
        return admissionRepository.save(existing);
    }

    public int deleteStudentAdmission(String admissionId) {
        admissionRepository.deleteById(admissionId);
        return 1;
    }

    public StudentAdmission getStudentAdmissionById(String admissionId) {
        return admissionRepository.findById(admissionId)
                .orElseThrow( () -> new RuntimeException("Student Admission not found for :"+admissionId));
    }

    public List<StudentAdmission> getStudentAdmissionByStudentId(String studentId) {
        return admissionRepository.findByStudentId(studentId);
    }

    public List<StudentAdmission> getAllStudentAdmission() {
        return admissionRepository.findAll();
    }
}
