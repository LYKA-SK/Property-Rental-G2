package com.mindvault.Property.services;

import com.mindvault.Property.dtos_request.LoginRequest;
import com.mindvault.Property.dtos_request.RegisterRequest;
import com.mindvault.Property.dtos_respone.AuthResponse;
import com.mindvault.Property.entities.User;

public interface AuthService {
    User register(RegisterRequest request);       // Step 1
    AuthResponse login(LoginRequest request);     // Step 2 & 4
    User assignAgentRole(Long userId);           // Step 3
}
