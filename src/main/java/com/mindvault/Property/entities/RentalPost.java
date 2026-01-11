package com.mindvault.Property.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rental_posts")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RentalPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String address;
    private Double price;
    private String phoneNumber;
    private String electricityCost;
    private String waterCost;

    @ManyToOne
    @JoinColumn(name = "user_id") 
    private User user;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}