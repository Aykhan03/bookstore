package com.aykhan.bookstore.backend.controller;

import com.aykhan.bookstore.backend.dto.LoginRequest;
import com.aykhan.bookstore.backend.dto.LoginResponse;
import com.aykhan.bookstore.backend.model.User;
import com.aykhan.bookstore.backend.security.JwtUtil;
import com.aykhan.bookstore.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userService.validatePassword(user, loginRequest.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(new LoginResponse(token, user.getEmail(), user.getName()));
            }
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Invalid email or password");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        
        User savedUser = userService.createUser(user);
        String token = jwtUtil.generateToken(savedUser.getEmail());
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new LoginResponse(token, savedUser.getEmail(), savedUser.getName()));
    }
}