package com.auth_service.entity;

import com.auth_service.entity.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Random Refresh Token
     */
    @Column(nullable = false, unique = true, length = 500)
    private String token;

    /**
     * Expiry Time
     */
    @Column(nullable = false)
    private LocalDateTime expiryDate;

    /**
     * Whether this token is revoked
     */
    @Builder.Default
    @Column(nullable = false)
    private Boolean revoked = false;

    /**
     * One User -> Many Refresh Tokens
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}