package org.usman.api.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usman.api.college.repository.entities.StudentAddress;

import java.util.List;

@Repository
public interface StudentAddressRepository extends JpaRepository<StudentAddress, Long>{

//    List<StudentAddress> findByStudentId(String studentId);
//    void deleteStudentAddressById(Long addressId);

}
