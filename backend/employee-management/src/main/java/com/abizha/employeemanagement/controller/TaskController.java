package com.abizha.employeemanagement.controller;

import com.abizha.employeemanagement.model.Task;
import com.abizha.employeemanagement.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }
    @GetMapping("/{id}")
public Task getTaskById(@PathVariable Long id) {
    return taskRepository.findById(id).orElse(null);
}
@GetMapping("/employee/{employeeId}")
public List<Task> getTasksByEmployee(@PathVariable Long employeeId) {
    return taskRepository.findByEmployeeId(employeeId);
}
@PutMapping("/{id}")
public Task updateTask(@PathVariable Long id, @RequestBody Task task) {

    Task existingTask = taskRepository.findById(id).orElse(null);

    if (existingTask != null) {
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setDeadline(task.getDeadline());
        existingTask.setEmployeeId(task.getEmployeeId());

        return taskRepository.save(existingTask);
    }

    return null;
   }
   @DeleteMapping("/{id}")
public String deleteTask(@PathVariable Long id) {

    if (taskRepository.existsById(id)) {
        taskRepository.deleteById(id);
        return "Task deleted successfully";
    }

    return "Task not found";
   }
   @PutMapping("/{id}/status")
public Task updateTaskStatus(@PathVariable Long id, @RequestParam String status) {

    Task existingTask = taskRepository.findById(id).orElse(null);

    if (existingTask != null) {
        existingTask.setStatus(status);
        return taskRepository.save(existingTask);
    }

    return null;
}
}