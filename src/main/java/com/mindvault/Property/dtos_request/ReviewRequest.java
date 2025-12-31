package com.mindvault.Property.dtos_request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequest {
    private Integer rating;
    private String comment;
    private Long userId;
    private Long rentalPostId;
}
