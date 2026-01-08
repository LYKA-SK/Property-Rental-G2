package com.mindvault.Property.repositories;

import com.mindvault.Property.entities.Review;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(r.rating) FROM Review r")
    Double findAverageRating();

    List<Review> findAll();
}
