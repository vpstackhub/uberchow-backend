package com.uberchow.controller;

import com.uberchow.model.User;
import com.uberchow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uberchow.dto.LoginRequest;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setAddress(updatedUser.getAddress());
            user.setCity(updatedUser.getCity());
            user.setCreditCardNumber(updatedUser.getCreditCardNumber());
            return ResponseEntity.ok(userRepository.save(user));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/end-user-login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}

