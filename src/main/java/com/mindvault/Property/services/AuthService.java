package com.mindvault.Property.services;

import com.mindvault.Property.entities.User;
import com.mindvault.Property.dtos_request.LoginRequest;
import com.mindvault.Property.dtos_request.RegisterRequest;
import com.mindvault.Property.dtos_respone.AuthResponse; // Add this

public interface AuthService {
    User register(RegisterRequest request);
    AuthResponse login(LoginRequest request); // Change return type to AuthResponse
}