package com.example.achivia.feature.oauth.payload.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthAccountResponseDto {
    private UUID id;
    private UUID userId;
    private String provider;
    private String providerAccountId;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
}
