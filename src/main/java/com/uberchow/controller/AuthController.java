package com.uberchow.controller;

import com.uberchow.dto.AuthRequest;
import com.uberchow.model.User;
import com.uberchow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody AuthRequest request) {
        System.out.println("Admin login attempt: " + request.getUsername() + ", " + request.getPassword());

        Optional<User> admin = userRepository.findByUsernameAndPasswordAndIsAdminTrue(
                request.getUsername(), request.getPassword());

        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid admin credentials");
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody AuthRequest request) {
        System.out.println("User login attempt: " + request.getUsername() + ", " + request.getPassword());

        Optional<User> user = userRepository.findByUsernameAndPasswordAndIsAdminFalse(
                request.getUsername(), request.getPassword());

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user credentials");
        }
    }

    @PostMapping("/user/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        user.setCreatedOn(LocalDateTime.now());
        user.setModifiedOn(LocalDateTime.now());
        user.setIsAdmin(false);

        return ResponseEntity.ok(userRepository.save(user));
    }
}

