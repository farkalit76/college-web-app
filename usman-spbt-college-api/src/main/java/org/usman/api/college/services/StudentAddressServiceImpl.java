package org.usman.api.college.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usman.api.college.repository.StudentAddressRepository;
import org.usman.api.college.repository.entities.Student;
import org.usman.api.college.repository.entities.StudentAddress;

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
@RequiredArgsConstructor
public class StudentAddressServiceImpl {

    private final StudentAddressRepository studentAddressRepository;

    public StudentAddress createStudentAddress(StudentAddress student) {
        return studentAddressRepository.save(student);
    }

    public StudentAddress updateStudentAddress(Long addressId, StudentAddress address) {
        StudentAddress existing = studentAddressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("StudentAddress not found: " + addressId));
        existing.setAddressLine1(address.getAddressLine1());
        existing.setAddressLine2(address.getAddressLine2());
        existing.setCity(address.getCity());
        existing.setState(address.getState());
        existing.setCountry(address.getCountry());
        existing.setPostalCode(address.getPostalCode());
        return studentAddressRepository.save(existing);
    }

    public int deleteStudentAddressById(Long addressId) {
        //studentAddressRepository.deleteStudentAddressById(addressId);
        return 1;
    }

    public StudentAddress getStudentAddressById(Long addressId) {
        return studentAddressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("StudentAddress not found: " + addressId));
    }

    public List<StudentAddress> getStudentAddressByStudentId(String studentId) {
        return null;//studentAddressRepository.findByStudentId(studentId);
    }

    public List<StudentAddress> getAllStudentAddresses() {
        return studentAddressRepository.findAll();
    }
}
