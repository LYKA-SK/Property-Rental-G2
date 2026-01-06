package com.mindvault.Property.entities;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating; // 1-5 stars

    @Column(columnDefinition = "TEXT")
    private String comment;

    private String username; // simple user info

    private Timestamp createdAt;

    // Automatically set createdAt before saving
    @PrePersist
    public void prePersist() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }
}
