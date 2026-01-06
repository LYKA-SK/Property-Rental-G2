package com.mindvault.Property.services;

import com.mindvault.Property.dtos_request.LoginRequest;
import com.mindvault.Property.dtos_request.RegisterRequest;
import com.mindvault.Property.dtos_respone.AuthResponse;

public interface AuthService {
    // Change return type from User to AuthResponse
    AuthResponse register(RegisterRequest request); 
    
    AuthResponse login(LoginRequest request);
}