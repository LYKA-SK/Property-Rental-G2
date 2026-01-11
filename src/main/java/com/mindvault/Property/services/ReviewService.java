package com.mindvault.Property.services;

import com.mindvault.Property.entities.Review;
import com.mindvault.Property.entities.RentalPost;
import com.mindvault.Property.entities.User;
import com.mindvault.Property.repositories.ReviewRepository;
import com.mindvault.Property.repositories.RentalPostRepository;
import com.mindvault.Property.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RentalPostRepository rentalPostRepository;
    private final UserRepository userRepository;

    @Transactional
    public Review addReview(Long userId, Long rentalPostId, int rating, String comment) {

        if (rating < 1 || rating > 5) {
            throw new RuntimeException("Rating must be between 1 and 5 stars");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RentalPost post = rentalPostRepository.findById(rentalPostId)
                .orElseThrow(() -> new RuntimeException("Rental post not found"));

        reviewRepository.findByUserAndRentalPost(user, post).ifPresent(r -> {
            throw new RuntimeException("You have already reviewed this property");
        });

        Review review = Review.builder()
                .user(user)
                .rentalPost(post)
                .rating(rating)
                .comment(comment)
                .build();

        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByRentalPost(RentalPost post) {
        return reviewRepository.findByRentalPost(post);
    }

    public double getAverageRating(RentalPost post) {
        List<Review> reviews = reviewRepository.findByRentalPost(post);
        if (reviews.isEmpty()) return 0.0;

        double sum = reviews.stream().mapToInt(Review::getRating).sum();
        return sum / reviews.size();
    }

    public long getReviewCount(RentalPost post) {
        return reviewRepository.countByRentalPost(post);
    }
}
