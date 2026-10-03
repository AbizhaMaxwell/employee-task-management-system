package com.abizha.employeemanagement.controller;

import com.abizha.employeemanagement.model.User;
import com.abizha.employeemanagement.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/login")
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public String login(@RequestBody User user) {

        Optional<User> existingUser =
                userRepository.findByUsername(user.getUsername());

        if (existingUser.isPresent() &&
            existingUser.get().getPassword().equals(user.getPassword())) {

            return "Login successful. Role: " + existingUser.get().getRole();
        }

        return "Invalid username or password";
    }
}