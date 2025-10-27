package org.usman.api.college.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.usman.api.college.repository.TeacherRepository;
import org.usman.api.college.repository.entities.Teacher;

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
public class TeacherServiceImpl {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher createTeacher(Teacher teacher) {
        log.info("Create Teacher as :{}", teacher);
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
    }



    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        return teacherRepository.findById(id).map(teacher -> {
            teacher.setFirstName(updatedTeacher.getFirstName());
            teacher.setLastName(updatedTeacher.getLastName());
            teacher.setDateOfBirth(updatedTeacher.getDateOfBirth());
            teacher.setGender(updatedTeacher.getGender());
            teacher.setHireDate(updatedTeacher.getHireDate());
            teacher.setAddress(updatedTeacher.getAddress());
            teacher.setPhoneNumber(updatedTeacher.getPhoneNumber());
            teacher.setEmail(updatedTeacher.getEmail());
            teacher.setQualification(updatedTeacher.getQualification());
            teacher.setExperience(updatedTeacher.getExperience());
            teacher.setSubjectExpert(updatedTeacher.getSubjectExpert());
            teacher.setIsActive(updatedTeacher.getIsActive());
            //teacher.setModifiedBy(updatedTeacher.getModifiedBy());
            return teacherRepository.save(teacher);
        }).orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
    }

    public String deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
        return "DELETE";
    }
}
