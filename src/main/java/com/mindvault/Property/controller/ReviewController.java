package com.mindvault.Property.controller;

import com.mindvault.Property.entities.Review;
import com.mindvault.Property.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // FIX: Changed @RequestParam to @RequestBody
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review createReview(@RequestBody Review review) {
        // This calls your service using the data from the JSON body
        return reviewService.createReview(
            review.getUsername(), 
            review.getRating(), 
            review.getComment()
        );
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }
}