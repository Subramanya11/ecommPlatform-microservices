package com.auth_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String token;

    private String tokenType;

    private String email;

    private String firstName;

    private String message;

}