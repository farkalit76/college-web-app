package org.usman.api.college.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usman.api.college.repository.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}