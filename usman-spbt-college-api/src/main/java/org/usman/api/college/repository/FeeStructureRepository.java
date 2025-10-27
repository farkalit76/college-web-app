package org.usman.api.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usman.api.college.repository.entities.FeeStructure;

@Repository
public interface FeeStructureRepository extends JpaRepository<FeeStructure, Long> {}
