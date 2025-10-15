package com.example.achivia.feature.userachievement.payload.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAchievementResponseDto {
    private UUID userId;
    private UUID achievementId;
    private LocalDateTime unlockedAt;
}
