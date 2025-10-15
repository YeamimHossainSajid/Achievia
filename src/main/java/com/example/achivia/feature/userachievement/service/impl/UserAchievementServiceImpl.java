package com.example.achivia.feature.userachievement.service.impl;
import com.example.achivia.feature.userachievement.entity.UserAchievement;
import com.example.achivia.feature.userachievement.payload.request.UserAchievementRequestDto;
import com.example.achivia.feature.userachievement.payload.response.UserAchievementResponseDto;
import com.example.achivia.feature.userachievement.repository.UserAchievementRepository;
import com.example.achivia.feature.userachievement.service.UserAchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAchievementServiceImpl implements UserAchievementService {

    private final UserAchievementRepository repository;

    @Override
    public UserAchievementResponseDto unlockAchievement(UserAchievementRequestDto requestDto) {
        LocalDateTime unlockedAt = LocalDateTime.now();
        repository.insertUserAchievement(requestDto.getUserId(), requestDto.getAchievementId(), unlockedAt);
        UserAchievement ua = repository.findByUserIdAndAchievementIdNative(requestDto.getUserId(), requestDto.getAchievementId());
        return mapToDto(ua);
    }

    @Override
    public List<UserAchievementResponseDto> getAchievementsByUser(UUID userId) {
        List<UserAchievement> achievements = repository.findByUserIdNative(userId);
        return achievements.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public UserAchievementResponseDto getUserAchievement(UUID userId, UUID achievementId) {
        UserAchievement ua = repository.findByUserIdAndAchievementIdNative(userId, achievementId);
        if (ua == null) throw new RuntimeException("Achievement not found for user");
        return mapToDto(ua);
    }

    private UserAchievementResponseDto mapToDto(UserAchievement ua) {
        return UserAchievementResponseDto.builder()
                .userId(ua.getUser().getId())
                .achievementId(ua.getAchievement().getId())
                .unlockedAt(ua.getUnlockedAt())
                .build();
    }
}
