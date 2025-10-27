package org.usman.api.college.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usman.api.college.repository.StudentRepository;
import org.usman.api.college.repository.entities.Student;

import java.util.List;
import java.util.Random;

/**
 * Implementation Note : This class is an....
 *
 * @author UsmanFarkalit
 * @date 10-07-2025
 * @since 1.0
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl {

    private final StudentRepository studentRepository;

    public Student createStudent(Student student) {
        student.setStudentId(getStudentId());
        log.info("Student with id :{}", student);
        return studentRepository.save(student);
    }

    public Student updateStudent(String studentId, Student student) {
        Student existing = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));
        existing.setFirstName(student.getFirstName());
        existing.setLastName(student.getLastName());
        existing.setEmail(student.getEmail());
        existing.setPhone(student.getPhone());
        return studentRepository.save(existing);
    }

    public int deleteStudent(String studentId) {
        studentRepository.deleteById(studentId);
        return 0;
    }

    public Student getStudentById(String studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    private String getStudentId(){
        Random random = new Random();
        return "STD"+ (random.nextInt(99000) + 1000);//start from 1000
    }
}
