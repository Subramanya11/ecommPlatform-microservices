package com.auth_service.service;

import com.auth_service.dto.request.LoginRequest;
import com.auth_service.dto.request.RegisterRequest;
import com.auth_service.dto.response.LoginResponse;
import com.auth_service.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);

}