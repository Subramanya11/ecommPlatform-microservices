package com.auth_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    // JWT Access Token
    private String accessToken;

    // Refresh Token
    private String refreshToken;

    // Bearer
    private String tokenType;


    private String email;

    private String firstName;
    // Token Expiry (15 minutes)
    private Long expiresIn;

    private String message;

}