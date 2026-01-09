// package com.mindvault.Property.entities;

// import jakarta.persistence.*;
// import lombok.*;
// import java.time.LocalDateTime;

// @Entity
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// @Table(
//     name = "review",
//     uniqueConstraints = {
//         @UniqueConstraint(columnNames = {"user_id", "rental_post_id"})
//     }
// )
// public class Review {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private int rating; // 1-5 only

//     @Column(columnDefinition = "TEXT")
//     private String comment;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "user_id", nullable = false)
//     private User user;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "rental_post_id", nullable = false)
//     private RentalPost rentalPost;

//     private LocalDateTime createdAt;

//     @PrePersist
//     protected void onCreate() {
//         this.createdAt = LocalDateTime.now();
//     }
// }

package com.mindvault.Property.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "rental_post_id"})
})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_post_id", nullable = false)
    private RentalPost rentalPost;

    @Column(nullable = false)
    private int rating; // 1 to 5

    private String comment;
}
