package com.auth_service.service;

import com.auth_service.dto.request.RegisterRequest;
import com.auth_service.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

}