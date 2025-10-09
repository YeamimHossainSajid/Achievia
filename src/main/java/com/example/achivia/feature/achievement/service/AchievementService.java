package com.example.achivia.feature.achievement.service;

import com.example.achivia.feature.achievement.payload.request.AchievementRequestDto;
import com.example.achivia.feature.achievement.payload.response.AchievementResponseDto;

import java.util.List;
import java.util.UUID;

public interface AchievementService {

    List<AchievementResponseDto> getAchievements();

    AchievementResponseDto getAchievementById(UUID id);

    AchievementResponseDto insertAchievement(AchievementRequestDto achievementRequestDto);

    AchievementResponseDto updateAchievement(UUID id, AchievementRequestDto achievementRequestDto);

    void deleteAchievement(UUID id);
}