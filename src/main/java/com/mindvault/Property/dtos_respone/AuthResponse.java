package com.mindvault.Property.dtos_respone;

import com.mindvault.Property.entities.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private int status;
    private String message;
    private String accessToken;
    private String refreshToken;
    private User user;
}
