package com.mindvault.Property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mindvault.Property.entities.User;
import com.mindvault.Property.services.AuthService;
import com.mindvault.Property.dtos_request.LoginRequest;
import com.mindvault.Property.dtos_request.RegisterRequest;
import com.mindvault.Property.dtos_respone.AuthResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
  //Register
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest request) {
        User newUser = authService.register(request);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    //Login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    //Assign ROLE_AGENT
    @PostMapping("/assign-agent/{userId}")
    public ResponseEntity<User> assignAgentRole(@PathVariable Long userId) {
        User updatedUser = authService.assignAgentRole(userId);
        return ResponseEntity.ok(updatedUser);
    }
}
