package com.org.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.demo.model.PatientData;

@Repository
public interface MainRepo extends JpaRepository<PatientData, Long> {

}
