package com.auth_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Get Authorization header
        String authHeader = request.getHeader("Authorization");

        // 2. If JWT is not present, continue normally
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);

            return;
        }

        // 3. Remove "Bearer " prefix
        String jwt = authHeader.substring(7);

        try {

            // 4. Extract email from JWT
            String email = jwtService.extractUsername(jwt);

            // 5. Only authenticate if SecurityContext is currently empty
            if (email != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                // 6. Load user from database
                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(email);

                // 7. Validate JWT
                if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {

                    // 8. Create Spring Security authentication
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    // 9. Attach request information
                    authentication.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    // 10. Store authentication in SecurityContext
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authentication);
                }
            }

        } catch (Exception ex) {

            // Invalid/expired JWT
            // Don't authenticate the request.
        }

        // 11. Continue request
        filterChain.doFilter(request, response);
    }
}