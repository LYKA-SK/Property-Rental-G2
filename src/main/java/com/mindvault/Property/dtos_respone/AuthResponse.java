package com.mindvault.Property.dtos_respone;

import com.mindvault.Property.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private int status;
    private String message;
    private String accessToken;
    private String refreshToken;
    private User user;
}