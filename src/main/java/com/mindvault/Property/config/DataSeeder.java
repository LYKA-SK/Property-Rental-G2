package com.mindvault.Property.config;

import com.mindvault.Property.entities.User;
import com.mindvault.Property.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            String adminEmail = "admin@property.com";

            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                User admin = new User();
                admin.setFullName("System Admin");
                admin.setEmail(adminEmail);
                admin.setPhone("012345678");
                admin.setPassword("admin123"); // Plain text password
                admin.setRole("ADMIN");
                admin.setCreatedAt(LocalDateTime.now());

                userRepository.save(admin);
                System.out.println("✅ SUCCESS: Admin seeded to Neon Database!");
            } else {
                System.out.println("ℹ️ INFO: Admin already exists.");
            }
        };
    }
}