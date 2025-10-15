package com.example.achivia.feature.userprofile.payload.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponseDto {
    private UUID id;
    private UUID userId;
    private String displayName;
    private String avatarUrl;
    private String bio;
    private String countryCode;
    private String timezone;
    private Integer level;
    private Long xp;
    private Integer streakDays;
    private LocalDateTime lastActiveAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
