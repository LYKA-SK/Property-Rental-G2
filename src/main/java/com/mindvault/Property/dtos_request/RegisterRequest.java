package com.mindvault.Property.dtos_request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String phone;
    // Getters and Setters
}