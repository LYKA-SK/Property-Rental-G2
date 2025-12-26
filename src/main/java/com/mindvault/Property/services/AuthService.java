package com.mindvault.Property.services;

import com.mindvault.Property.dtos_request.RegisterRequest;
import com.mindvault.Property.dtos_respone.RegisterResponse;
import com.mindvault.Property.entities.User;
import com.mindvault.Property.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    // Use the bean from SecurityConfig instead of 'new'
    private final BCryptPasswordEncoder passwordEncoder; 

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                // Add this line to satisfy the database constraint
                .role("ROLE_USER") 
                .build();

        userRepository.save(user);

        return RegisterResponse.builder()
                .message("Register successful")
                .email(user.getEmail())
                .build();
    }
}