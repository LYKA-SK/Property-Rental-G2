package com.mindvault.Property.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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

    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "rental_post_id", nullable = false)
    private Long rentalPostId;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
