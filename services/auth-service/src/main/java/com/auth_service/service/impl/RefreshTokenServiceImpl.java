package com.auth_service.service.impl;

import com.auth_service.entity.RefreshToken;
import com.auth_service.entity.User;
import com.auth_service.repository.RefreshTokenRepository;
import com.auth_service.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-expiration}")
    private Long refreshTokenDuration;

    @Override
    public RefreshToken createRefreshToken(User user) {

        refreshTokenRepository.deleteByUser(user);

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(
                        LocalDateTime.now().plusSeconds(refreshTokenDuration / 1000)
                )
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(String token) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Refresh Token Not Found"));

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {

            refreshTokenRepository.delete(refreshToken);

            throw new RuntimeException("Refresh Token Expired");
        }

        return refreshToken;
    }

    @Override
    public void deleteByUser(User user) {

        refreshTokenRepository.deleteByUser(user);


    }

}