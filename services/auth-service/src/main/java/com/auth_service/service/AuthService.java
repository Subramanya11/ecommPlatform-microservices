package com.auth_service.service;

import com.auth_service.dto.request.LoginRequest;
import com.auth_service.dto.request.RefreshTokenRequest;
import com.auth_service.dto.request.RegisterRequest;
import com.auth_service.dto.response.LoginResponse;
import com.auth_service.dto.response.RefreshTokenResponse;
import com.auth_service.dto.response.RegisterResponse;
import com.auth_service.dto.response.UserProfileResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    UserProfileResponse getCurrentUser();

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
}