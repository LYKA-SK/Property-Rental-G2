package com.mindvault.Property.entities;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

=======
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
>>>>>>> fe290d89f9c67bb8cc135ab4e9cf0c6e9b96d4d8
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;
}
=======
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String phone;
    private String role; // Added a simple role column
    private LocalDateTime createdAt;

    // Getters and Setters
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getEmail() { return email; }
}
>>>>>>> fe290d89f9c67bb8cc135ab4e9cf0c6e9b96d4d8
