package com.mindvault.Property.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindvault.Property.entities.Role;
import com.mindvault.Property.entities.User;
import com.mindvault.Property.repositories.RoleRepository;
import com.mindvault.Property.repositories.UserRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            String adminEmail = "admin@property.com";

            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                // 1. Find the actual Role object from the database
                Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                        .orElseThrow(() -> new RuntimeException("Error: ROLE_ADMIN not found. Run RoleSeeder first!"));

                // 2. Create the Admin user
                User admin = User.builder()
                        .fullName("System Admin")
                        .email(adminEmail)
                        .phone("012345678")
                        .password("admin123") // Note: Should use passwordEncoder.encode("admin123") later
                        .build();

                // 3. Add the role object to the user
                admin.addRole(adminRole);

                userRepository.save(admin);
                System.out.println("✅ SUCCESS: Admin seeded with ROLE_ADMIN!");
            } else {
                System.out.println("ℹ️ INFO: Admin already exists.");
            }
        };
    }
}