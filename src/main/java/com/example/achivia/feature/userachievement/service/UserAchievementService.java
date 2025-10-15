package com.example.achivia.feature.userachievement.service;

import com.example.achivia.feature.userachievement.payload.request.UserAchievementRequestDto;
import com.example.achivia.feature.userachievement.payload.response.UserAchievementResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserAchievementService {
    UserAchievementResponseDto unlockAchievement(UserAchievementRequestDto requestDto);
    List<UserAchievementResponseDto> getAchievementsByUser(UUID userId);
    UserAchievementResponseDto getUserAchievement(UUID userId, UUID achievementId);
}
