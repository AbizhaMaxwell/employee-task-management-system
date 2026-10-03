package com.abizha.employeemanagement.controller;

import com.abizha.employeemanagement.model.Employee;
import com.abizha.employeemanagement.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        Employee existingEmployee =
                employeeRepository.findById(id).orElse(null);

        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setDesignation(employee.getDesignation());

            return employeeRepository.save(existingEmployee);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return "Employee deleted successfully";
        }

        return "Employee not found";
    }
}