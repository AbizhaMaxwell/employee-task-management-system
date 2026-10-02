package com.abizha.employeemanagement.controller;

import com.abizha.employeemanagement.model.Employee;
import com.abizha.employeemanagement.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

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