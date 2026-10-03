package com.abizha.employeemanagement.controller;

import com.abizha.employeemanagement.repository.EmployeeRepository;
import com.abizha.employeemanagement.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;

    public DashboardController(EmployeeRepository employeeRepository,
                                TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public Map<String, Object> getDashboard() {

        long totalEmployees = employeeRepository.count();
        long totalTasks = taskRepository.count();
        long completedTasks = taskRepository.countByStatus("Completed");
        long pendingTasks = taskRepository.countByStatus("Pending");

        double completionPercentage = 0;

        if (totalTasks > 0) {
            completionPercentage = (completedTasks * 100.0) / totalTasks;
        }

        Map<String, Object> dashboard = new HashMap<>();

        dashboard.put("totalEmployees", totalEmployees);
        dashboard.put("totalTasks", totalTasks);
        dashboard.put("completedTasks", completedTasks);
        dashboard.put("pendingTasks", pendingTasks);
        dashboard.put("completionPercentage", completionPercentage);

        return dashboard;
    }
}