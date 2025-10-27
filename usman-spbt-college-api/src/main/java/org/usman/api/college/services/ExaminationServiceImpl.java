package org.usman.api.college.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usman.api.college.repository.ExaminationRepository;
import org.usman.api.college.repository.entities.Examination;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExaminationServiceImpl {

    private final ExaminationRepository examinationRepository;

    public Examination createExamination(Examination examination) {
        return examinationRepository.save(examination);
    }

    public Examination updateExamination(Long examId, Examination examination) {
        Examination existing = examinationRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Examination not found: " + examId));
        existing.setSemesterId(examination.getSemesterId());
        existing.setExamName(examination.getExamName());
        existing.setExamDate(examination.getExamDate());
        return examinationRepository.save(examination);
    }

    public int deleteExamination(Long examId) {
        examinationRepository.deleteById(examId);
        return 1;
    }

    public Examination getExaminationById(Long examId) {
        return examinationRepository.findById(examId)
                .orElseThrow( () -> new RuntimeException("Examination not found for :"+examId));
    }

    public List<Examination> getAllExaminations() {
        return examinationRepository.findAll();
    }
}
