package com.abizha.employeemanagement.controller;

import com.abizha.employeemanagement.model.User;
import com.abizha.employeemanagement.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginUser) {

        Optional<User> user =
                userRepository.findByUsername(
                        loginUser.getUsername()
                );

        if (user.isPresent() &&
                user.get().getPassword()
                        .equals(loginUser.getPassword())) {

            return user.get();

        }

        return null;
    }
}