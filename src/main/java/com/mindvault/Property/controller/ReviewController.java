package com.mindvault.Property.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.mindvault.Property.entities.Review;
import com.mindvault.Property.dtos_request.ReviewRequest;
import com.mindvault.Property.services.ReviewService;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // Create a review
    @PostMapping
    public Review create(@RequestBody ReviewRequest request) {
        return reviewService.createReview(request);
    }

    // Get all reviews
    @GetMapping
    public List<Review> getAll() {
        return reviewService.getAllReviews();
    }

    // Get reviews by rental post
    @GetMapping("/by-post/{postId}")
    public List<Review> getByPost(@PathVariable Long postId) {
        return reviewService.getReviewsByPost(postId);
    }

    // Get reviews by user
    @GetMapping("/by-user/{userId}")
    public List<Review> getByUser(@PathVariable Long userId) {
        return reviewService.getReviewsByUser(userId);
    }

    // Search reviews by keyword
    @GetMapping("/search")
    public List<Review> searchByKeyword(@RequestParam String keyword) {
        return reviewService.searchReviewsByKeyword(keyword);
    }

    // Get reviews by rating
    @GetMapping("/by-rating/{rating}")
    public List<Review> getByRating(@PathVariable Integer rating) {
        return reviewService.getReviewsByRating(rating);
    }
}
