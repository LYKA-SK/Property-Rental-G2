package com.mindvault.Property.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mindvault.Property.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
