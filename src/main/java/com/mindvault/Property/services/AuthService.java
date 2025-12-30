package com.mindvault.Property.services;

import com.mindvault.Property.dtos_request.RegisterRequest;
import com.mindvault.Property.dtos_respone.RegisterResponse;
import com.mindvault.Property.entities.Role;
import com.mindvault.Property.entities.User;
import com.mindvault.Property.repositories.RoleRepository; // Added this
import com.mindvault.Property.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository; // 1. Added RoleRepository
    private final BCryptPasswordEncoder passwordEncoder; 

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Create user and encode password
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Added encoding
                .phone(request.getPhone())
                .build();

        // 2. Correct way to find the Role object
        Role userRole = roleRepository.findByName("ROLE_USER")
        	    .orElseThrow(() -> new RuntimeException("Error: Role not found."));

        	// 2. Add it to the user
        	user.addRole(userRole);

        userRepository.save(user);

        return RegisterResponse.builder()
                .message("Register successful")
                .email(user.getEmail())
                .build();
    }
}