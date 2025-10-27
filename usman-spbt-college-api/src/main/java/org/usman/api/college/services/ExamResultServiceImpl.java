package org.usman.api.college.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usman.api.college.repository.ExamResultRepository;
import org.usman.api.college.repository.entities.ExamResult;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl {

    private final ExamResultRepository examResultRepository;

    public ExamResult createExamResult(ExamResult examResult) {
        return examResultRepository.save(examResult);
    }

    public ExamResult updateExamResult(Long resultId, ExamResult examResult) {
        //return null;
        ExamResult existing = examResultRepository.findById(resultId)
                .orElseThrow(() -> new RuntimeException("examResult not found for: " + resultId));
        existing.setExamId(examResult.getExamId());
        existing.setStudentId(examResult.getStudentId());
        existing.setSubjectId(examResult.getSubjectId());
        existing.setMarksObtained(examResult.getMarksObtained());
        existing.setGrade(examResult.getGrade());
        return examResultRepository.save(existing);
    }

    public int deleteExamResult(Long resultId) {
        examResultRepository.deleteById(resultId);
        return 1;
    }

    public ExamResult getExamResultById(Long resultId) {
        return examResultRepository.findById(resultId)
                .orElseThrow( () -> new RuntimeException("ExamResult not found for :"+resultId));
    }

    public List<ExamResult> getExamResultByStudentId(String studentId) {
        return examResultRepository.findByStudentId(studentId);
    }

    public List<ExamResult> getAllExamResults() {
        return examResultRepository.findAll();
    }
}
