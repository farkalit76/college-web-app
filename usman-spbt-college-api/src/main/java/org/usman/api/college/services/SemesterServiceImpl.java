package org.usman.api.college.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usman.api.college.repository.SemesterRepository;
import org.usman.api.college.repository.entities.Semester;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SemesterServiceImpl {

    private final SemesterRepository semesterRepository;

    public Semester createSemester(Semester semester) {
        return semesterRepository.save(semester);
    }

    public Semester updateSemester(Long semesterId, Semester semester) {
        Semester existing = semesterRepository.findById(semesterId)
                .orElseThrow(() -> new RuntimeException("semester not found: " + semesterId));
        existing.setCourseCode(semester.getCourseCode());
        existing.setSemesterNo(semester.getSemesterNo());
        existing.setStartDate(semester.getStartDate());
        existing.setEndDate(semester.getEndDate());
        return semesterRepository.save(existing);
    }

    public int deleteSemester(Long semesterId) {
        semesterRepository.deleteById(semesterId);
        return 1;
    }

    public Semester getSemesterById(Long semesterId) {
        return semesterRepository.findById(semesterId)
                .orElseThrow( () -> new RuntimeException("semester not found: " + semesterId));
    }

    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }
}
