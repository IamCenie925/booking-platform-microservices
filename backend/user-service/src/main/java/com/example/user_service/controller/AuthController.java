package com.example.user_service.controller;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map; 
import java.util.Objects;

@RestController
@RequestMapping("/api") // URL gốc là /api
@RequiredArgsConstructor // Lombok: tự động tiêm (autowire) UserRepository

public class AuthController {
    private final UserRepository repo;

    // API: POST /api/users/register
    @PostMapping("/users/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) { 
        User savedUser = repo.save(user);
        return ResponseEntity.status(201).body(savedUser);
    }

    // API: POST /api/auth/login
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        String username = req.get("username");
        String password = req.get("password");

        // Logic demo: tìm user và so sánh mật khẩu 
        return repo.findByUsername(username)
                .filter(u -> Objects.equals(u.getPassword(), password))
                .map(u -> ResponseEntity.ok(
                        // Trả về token demo và userId
                        Map.of("token", "DEMO_TOKEN_FOR_" + u.getUsername(), "userId", u.getId())
                ))
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Invalid credentials")));
    }

    // API: GET /api/users/{id}
    @GetMapping("/users/{id}")
    public ResponseEntity<?> profile(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
