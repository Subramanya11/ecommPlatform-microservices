package com.auth_service.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private UUID uuid;

    private String firstName;

    private String lastName;

    private String email;

    private String message;
}