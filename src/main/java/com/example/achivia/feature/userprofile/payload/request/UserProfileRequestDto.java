package com.example.achivia.feature.userprofile.payload.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRequestDto {
    private UUID userId;
    private String displayName;
    private String avatarUrl;
    private String bio;
    private String countryCode;
    private String timezone;
    private Integer level;
    private Long xp;
    private Integer streakDays;
}
