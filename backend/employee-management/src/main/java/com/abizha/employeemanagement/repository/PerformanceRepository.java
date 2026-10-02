package com.abizha.employeemanagement.repository;

import com.abizha.employeemanagement.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}