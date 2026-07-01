package com.auth_service.service.impl;

import com.auth_service.dto.request.RegisterRequest;
import com.auth_service.dto.response.RegisterResponse;
import com.auth_service.entity.Role;
import com.auth_service.entity.User;
import com.auth_service.entity.enums.RoleName;
import com.auth_service.exception.EmailAlreadyExistsException;
import com.auth_service.repository.RoleRepository;
import com.auth_service.repository.UserRepository;
import com.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        // Step 1: Check duplicate email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        // Step 2: Get default role
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        // Step 3: Create User entity
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .enabled(true)
                .accountNonLocked(true)
                .build();

        // Step 4: Assign role
        user.setRoles(Set.of(userRole));

        // Step 5: Save user
        User savedUser = userRepository.save(user);

        // Step 6: Build response
        return RegisterResponse.builder()
                .uuid(savedUser.getUuid()) // Change this if your UUID field has a different name
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .message("Registration Successful")
                .build();

    }
}