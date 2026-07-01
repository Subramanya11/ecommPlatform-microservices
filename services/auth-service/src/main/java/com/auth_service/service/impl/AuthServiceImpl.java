package com.auth_service.service.impl;

import com.auth_service.dto.request.RegisterRequest;
import com.auth_service.dto.response.RegisterResponse;
import com.auth_service.repository.RoleRepository;
import com.auth_service.repository.UserRepository;
import com.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        return null;

    }
}