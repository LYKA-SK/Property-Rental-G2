package com.mindvault.Property.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mindvault.Property.entities.Role;
import com.mindvault.Property.entities.User; // IMPORTANT IMPORT
import com.mindvault.Property.repositories.RoleRepository;
import com.mindvault.Property.repositories.UserRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Seed Roles first if they don't exist
            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }
            
            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                Role userRole = new Role();
                userRole.setName("ROLE_USER");
                roleRepository.save(userRole);
            }

            String adminEmail = "admin@property.com";
            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();

                User admin = User.builder()
                        .fullName("System Admin")
                        .email(adminEmail)
                        .phone("012345678")
                        .password(passwordEncoder.encode("admin123"))
                        .build();

                admin.addRole(adminRole);
                userRepository.save(admin);
                System.out.println(" Admin seeded!");
            }
        };
    }
}