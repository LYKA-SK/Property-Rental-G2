package com.mindvault.Property.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rental_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String address;
    private Double price;
    private Double electricityCost;
    private Double waterCost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
