package com.mindvault.Property.config;

import com.mindvault.Property.entities.Role;
import com.mindvault.Property.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    // Spring will automatically inject your RoleRepository here
    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Define the roles based on your ERD requirements
        List<String> roleNames = List.of("ROLE_USER", "ROLE_AGENT", "ROLE_ADMIN");

        for (String roleName : roleNames) {
            // Check if the role already exists to avoid duplicates
            if (roleRepository.findByName(roleName).isEmpty()) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
                System.out.println("Successfully seeded role: " + roleName);
            }
        }
    }
}