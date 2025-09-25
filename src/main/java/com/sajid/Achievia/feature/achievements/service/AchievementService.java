package com.sajid.Achievia.feature.achievements.service;

import com.sajid.Achievia.feature.achievements.entity.Achievement;
import com.sajid.Achievia.feature.achievements.payload.request.AchievementRequestDto;
import com.sajid.Achievia.feature.achievements.payload.response.AchievementResponseDto;

import java.util.List;

public interface AchievementService {

    public void createAchievement(AchievementRequestDto achievement);
    public List<AchievementResponseDto> getAchievements();
    public AchievementResponseDto getAchievement(Long id);
    public void updateAchievement(Long id ,AchievementRequestDto achievementRequestDto);
    public void deleteAchievement(Long id);

}
