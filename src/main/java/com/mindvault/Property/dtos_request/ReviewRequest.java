package com.mindvault.Property.dtos_request;

public class ReviewRequest {

    private Integer rating;
    private String comment;
    private Long userId;
    private Long rentalPostId;

    // getters and setters
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRentalPostId() {
        return rentalPostId;
    }

    public void setRentalPostId(Long rentalPostId) {
        this.rentalPostId = rentalPostId;
    }
}
