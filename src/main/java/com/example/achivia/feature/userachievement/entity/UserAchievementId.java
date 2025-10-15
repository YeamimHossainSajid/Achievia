package com.example.achivia.feature.userachievement.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAchievementId implements java.io.Serializable {
    private UUID userId;
    private UUID achievementId;
}