package com.auth_service.service;

import com.auth_service.entity.RefreshToken;
import com.auth_service.entity.User;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    RefreshToken verifyExpiration(String token);

    void deleteByUser(User user);

}