package com.example.achivia.feature.achievement.payload.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class AchievementRequestDto {

    @NotBlank(message = "Slug is required")
    private String slug;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    private String iconUrl;

    @NotNull(message = "Points reward is required")
    private Integer pointsReward = 0;
}

