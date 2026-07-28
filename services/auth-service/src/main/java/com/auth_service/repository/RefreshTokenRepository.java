package com.auth_service.repository;

import com.auth_service.entity.RefreshToken;
import com.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    /**
     * Find a refresh token by its token value.
     */
    Optional<RefreshToken> findByToken(String token);

    /**
     * Delete all refresh tokens of a user.
     */
    void deleteByUser(User user);

    /**
     * Check whether a token exists.
     */
    boolean existsByToken(String token);
}