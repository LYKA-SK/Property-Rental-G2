package com.mindvault.Property.controller;

import com.mindvault.Property.entities.Review;
import com.mindvault.Property.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // Add a review
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    // Get average rating
    @GetMapping("/average")
    public ResponseEntity<BigDecimal> getAverage() {
        return ResponseEntity.ok(reviewService.getAverageRating());
    }

    // Get all reviews
    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAll() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }
}
