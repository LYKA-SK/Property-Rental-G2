package com.mindvault.Property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mindvault.Property.entities.User;
import com.mindvault.Property.services.AuthService;
import com.mindvault.Property.dtos_request.LoginRequest;
import com.mindvault.Property.dtos_request.RegisterRequest;
import com.mindvault.Property.dtos_respone.AuthResponse; // THIS IMPORT WAS MISSING

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        if (response.getStatus() == 200) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}