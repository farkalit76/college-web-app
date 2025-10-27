package org.usman.api.college.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.usman.api.college.repository.CourseRepository;
import org.usman.api.college.repository.entities.Course;

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
public class CourseServiceImpl {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(String courseId, Course course) {
        return null;
    }

    public int deleteCourse(String courseId) {
        return 0;
    }

    public Course getCourseById(String courseId) {
        return null;
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id).map(course -> {
            course.setCourseName(updatedCourse.getCourseName());
//            course.setSubjects(updatedCourse.getSubjects());
//            course.setDescriptions(updatedCourse.getDescriptions());
//            course.setCredits(updatedCourse.getCredits());
//            course.setIsActive(updatedCourse.getIsActive());
//            course.setModifiedBy(updatedCourse.getModifiedBy());
            return courseRepository.save(course);
        }).orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public String deleteCourse(Long id) {
        courseRepository.deleteById(id);
        return "DELETED";
    }
}
