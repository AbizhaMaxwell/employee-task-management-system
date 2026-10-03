package com.abizha.employeemanagement.controller;

import com.abizha.employeemanagement.model.Performance;
import com.abizha.employeemanagement.repository.PerformanceRepository;
import org.springframework.web.bind.annotation.*;
import com.abizha.employeemanagement.repository.TaskRepository;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/performance")
public class PerformanceController {

    private final PerformanceRepository performanceRepository;
    private final TaskRepository taskRepository;

    public PerformanceController(PerformanceRepository performanceRepository,TaskRepository taskRepository) {
    this.performanceRepository = performanceRepository;
    this.taskRepository = taskRepository;
}

    @GetMapping
    public List<Performance> getAllPerformance() {
        return performanceRepository.findAll();
    }

    @PostMapping
    public Performance addPerformance(@RequestBody Performance performance) {
        return performanceRepository.save(performance);
    }

    @GetMapping("/{id}")
    public Performance getPerformanceById(@PathVariable Long id) {
        return performanceRepository.findById(id).orElse(null);
    }
    @GetMapping("/employee/{employeeId}")
public Performance calculatePerformance(@PathVariable Long employeeId) {

    long completed = taskRepository.countByEmployeeIdAndStatus(employeeId, "Completed");
    long pending = taskRepository.countByEmployeeIdAndStatus(employeeId, "Pending");

    long total = completed + pending;

    double percentage = 0;

    if (total > 0) {
        percentage = (completed * 100.0) / total;
    }

    Performance performance = new Performance();

    performance.setEmployeeId(employeeId);
    performance.setCompletedTasks((int) completed);
    performance.setPendingTasks((int) pending);
    performance.setCompletionPercentage(percentage);

    return performance;
   }
}