package org.usman.api.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usman.api.college.repository.entities.ExamResult;

import java.util.List;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {

    List<ExamResult> findByStudentId(String studentId);
}
