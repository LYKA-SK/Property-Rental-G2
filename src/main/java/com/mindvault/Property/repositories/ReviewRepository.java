package com.mindvault.Property.repositories;

import com.mindvault.Property.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
