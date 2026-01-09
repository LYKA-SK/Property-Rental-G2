// package com.mindvault.Property.dtos_request;

// import lombok.*;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class ReviewRequest {
//     private Integer rating;      // 1-5
//     private String comment;      // optional
//     private Long rentalPostId;   // the property being reviewed
// }
package com.mindvault.Property.dtos_request;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long rentalPostId;
    private int rating;
    private String comment;
}
