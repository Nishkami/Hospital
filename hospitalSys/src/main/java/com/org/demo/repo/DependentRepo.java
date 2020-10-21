package com.org.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.demo.model.Dependent;

@Repository
public interface DependentRepo extends JpaRepository<Dependent, Long> {

}
