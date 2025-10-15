package com.example.achivia.feature.oauth.payload.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthAccountRequestDto {
    private UUID userId;
    private String provider;
    private String providerAccountId;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expiresAt;
}
