package com.example.achivia.feature.achievement.payload.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AchievementResponseDto {

    private UUID id;
    private String slug;
    private String name;
    private String description;
    private String iconUrl;
    private Integer pointsReward;
    private LocalDateTime createdAt;
}

