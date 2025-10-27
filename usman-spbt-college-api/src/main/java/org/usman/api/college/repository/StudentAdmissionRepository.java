package org.usman.api.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usman.api.college.repository.entities.StudentAdmission;

import java.util.List;

@Repository
public interface StudentAdmissionRepository extends JpaRepository<StudentAdmission, String> {

    List<StudentAdmission> findByStudentId(String studentId);
//
//    List<StudentAdmission> findByCourseId(String courseId);
//
//    List<StudentAdmission> findByStudentIdAndCourseId(String studentId, String courseId);
}