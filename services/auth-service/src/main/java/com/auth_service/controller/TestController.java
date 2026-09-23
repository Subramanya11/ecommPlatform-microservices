package com.auth_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/protected")
    public String protectedApi(Authentication authentication) {

        return "Hello " + authentication.getName()
                + ", you are authenticated!";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userApi(Authentication authentication) {

        return "USER API - Hello "
                + authentication.getName();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminApi(Authentication authentication) {

        return "ADMIN API - Hello "
                + authentication.getName();
    }

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/seller")
    public String sellerApi(Authentication authentication) {

        return "SELLER API - Hello "
                + authentication.getName();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    @GetMapping("/admin-or-seller")
    public String adminOrSellerApi(
            Authentication authentication) {

        return "ADMIN or SELLER API - Hello "
                + authentication.getName();
    }
}