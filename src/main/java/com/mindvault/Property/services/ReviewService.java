package com.mindvault.Property.services;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.mindvault.Property.entities.Review;
import com.mindvault.Property.repositories.ReviewRepository;
import com.mindvault.Property.dtos_request.ReviewRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // Create review
    public Review createReview(ReviewRequest request) {
        Review review = Review.builder()
                .rating(request.getRating())
                .comment(request.getComment())
                .userId(request.getUserId())
                .rentalPostId(request.getRentalPostId())
                .createdAt(LocalDateTime.now())
                .build();
        return reviewRepository.save(review);
    }

    // Get all reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Search by rental post
    public List<Review> getReviewsByPost(Long postId) {
        return reviewRepository.findByRentalPostId(postId);
    }

    // Search by user
    public List<Review> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    // Search by keyword
    public List<Review> searchReviewsByKeyword(String keyword) {
        return reviewRepository.findByCommentContaining(keyword);
    }

    // Search by rating
    public List<Review> getReviewsByRating(Integer rating) {
        return reviewRepository.findByRating(rating);
    }
}
