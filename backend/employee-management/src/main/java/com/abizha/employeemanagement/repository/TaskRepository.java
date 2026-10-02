package com.abizha.employeemanagement.repository;

import com.abizha.employeemanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByEmployeeId(Long employeeId);

    long countByEmployeeIdAndStatus(Long employeeId, String status);
}