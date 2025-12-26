package com.mindvault.Property.dtos_response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private String fullName;
    private String message;
    private String email;
     private String phone;
}
