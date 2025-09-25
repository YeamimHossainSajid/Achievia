package com.sajid.Achievia.feature.achievements.service.impl;

import com.sajid.Achievia.feature.achievements.entity.Achievement;
import com.sajid.Achievia.feature.achievements.payload.request.AchievementRequestDto;
import com.sajid.Achievia.feature.achievements.payload.response.AchievementResponseDto;
import com.sajid.Achievia.feature.achievements.repository.AchievementRepository;
import com.sajid.Achievia.feature.achievements.service.AchievementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchievementServiceImpl implements AchievementService {

    private AchievementRepository achievementRepository;

    public AchievementServiceImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }


    public Achievement convertToAchievement(AchievementRequestDto achievementRequestDto) {

        Achievement achievement = new Achievement();

        achievement.setAchievementName(achievementRequestDto.getAchievementName());
        achievement.setDescription(achievementRequestDto.getDescription());

        return achievement;
    }

    public AchievementResponseDto convertToDto(Achievement achievement) {

        AchievementResponseDto achievementResponseDto = new AchievementResponseDto();

        achievementResponseDto.setId(achievement.getId());
        achievementResponseDto.setDescription(achievement.getDescription());
        achievementResponseDto.setAchievementName(achievement.getAchievementName());

        return achievementResponseDto;

    }


    @Override
    public void createAchievement(AchievementRequestDto achievementRequestDto) {
       achievementRepository.save(convertToAchievement(achievementRequestDto));
    }

    @Override
    public List<AchievementResponseDto> getAchievements() {

        List<Achievement> achievements = achievementRepository.findAll();

        return achievements.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AchievementResponseDto getAchievement(Long id) {

        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found with id: " + id));

        return convertToDto(achievement);
    }

    @Override
    public void updateAchievement(Long id,AchievementRequestDto achievementRequestDto) {

        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found with id: " + id));

        achievement.setDescription(achievementRequestDto.getDescription());
        achievement.setAchievementName(achievementRequestDto.getAchievementName());

        achievementRepository.save(achievement);
    }

    @Override
    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }
}
