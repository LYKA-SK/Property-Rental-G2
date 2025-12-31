package com.mindvault.Property.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mindvault.Property.entities.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRentalPostId(Long rentalPostId);

    List<Review> findByUserId(Long userId);

    List<Review> findByCommentContaining(String keyword);

    List<Review> findByRating(Integer rating);
}
