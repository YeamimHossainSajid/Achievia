package com.example.achivia.feature.userachievement.payload.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAchievementRequestDto {
    private UUID userId;
    private UUID achievementId;
}
