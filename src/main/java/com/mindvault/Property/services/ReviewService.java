package com.mindvault.Property.services;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.mindvault.Property.dtos_request.ReviewRequest;
import com.mindvault.Property.entities.Review;
import com.mindvault.Property.repositories.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review createReview(ReviewRequest request) {

        
        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setUserId(request.getUserId());
        review.setRentalPostId(request.getRentalPostId());

        return reviewRepository.save(review);
    }
}
