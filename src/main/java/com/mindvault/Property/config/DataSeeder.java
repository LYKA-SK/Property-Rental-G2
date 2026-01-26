package com.mindvault.Property.config;

import com.mindvault.Property.entities.Role;
import com.mindvault.Property.entities.User;
import com.mindvault.Property.repositories.RoleRepository;
import com.mindvault.Property.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = Role.builder().name("ADMIN").build();
        Role userRole = Role.builder().name("USER").build();
        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        User admin = User.builder()
                .fullName("Admin User")
                .email("admin@example.com")
                .password("adminpass")
                .roles(Set.of(adminRole))
                .build();
        userRepository.save(admin);
    }
}
