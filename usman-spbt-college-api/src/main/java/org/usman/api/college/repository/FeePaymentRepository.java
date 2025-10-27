package org.usman.api.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usman.api.college.repository.entities.FeePayment;

import java.util.List;

@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {
}
