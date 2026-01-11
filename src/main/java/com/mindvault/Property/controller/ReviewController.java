package com.mindvault.Property.controller;

import com.mindvault.Property.dtos_request.ReviewRequest;
import com.mindvault.Property.entities.Review;
import com.mindvault.Property.entities.RentalPost;
import com.mindvault.Property.repositories.RentalPostRepository;
import com.mindvault.Property.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final RentalPostRepository rentalPostRepository;

    // --- Submit a Review ---
    @PostMapping
    public ResponseEntity<?> addReview(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ReviewRequest reviewRequest
    ) {
        Long userId = ((com.mindvault.Property.entities.User) userDetails).getId();

        Review review = reviewService.addReview(
                userId,
                reviewRequest.getRentalPostId(),
                reviewRequest.getRating(),
                reviewRequest.getComment()
        );

        return ResponseEntity.ok(review);
    }

    // --- Get Reviews + Rating Summary for a Rental Post ---
    @GetMapping("/{rentalPostId}")
    public ResponseEntity<?> getReviews(@PathVariable Long rentalPostId) {
        RentalPost post = rentalPostRepository.findById(rentalPostId)
                .orElseThrow(() -> new RuntimeException("Rental post not found"));

        List<Review> reviews = reviewService.getReviewsByRentalPost(post);
           
        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviews);
        response.put("averageRating", reviewService.getAverageRating(post));
        response.put("reviewCount", reviewService.getReviewCount(post));

        return ResponseEntity.ok(response);
    }

    // --- List All Rental Posts with Ratings ---
    @GetMapping("/rental-posts")
    public ResponseEntity<?> getRentalPosts() {
        List<RentalPost> posts = rentalPostRepository.findAll();

        List<Map<String, Object>> result = posts.stream().map(post -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", post.getId());
            map.put("title", post.getTitle());
            map.put("price", post.getPrice());
            map.put("averageRating", reviewService.getAverageRating(post));
            map.put("reviewCount", reviewService.getReviewCount(post));
            return map;
        }).toList();

        return ResponseEntity.ok(result);
    }
}
