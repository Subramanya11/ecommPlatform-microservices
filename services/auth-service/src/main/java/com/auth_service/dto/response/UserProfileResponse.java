package com.auth_service.dto.response;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {

    private UUID uuid;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Set<String> roles;
}