package com.mindvault.Property.services;

import com.mindvault.Property.entities.Review;
import com.mindvault.Property.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // Add a review
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    // Get average rating
    public BigDecimal getAverageRating() {
        Double avg = reviewRepository.findAverageRating();
        return avg == null ? BigDecimal.ZERO : BigDecimal.valueOf(avg);
    }

    // Get all reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
