package com.example.achivia.feature.userstats.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.userstats.entity.UserStats;
import com.example.achivia.feature.userstats.payload.request.UserStatsRequestDto;
import com.example.achivia.feature.userstats.payload.response.UserStatsResponseDto;
import com.example.achivia.feature.userstats.repository.UserStatsRepository;
import com.example.achivia.feature.userstats.service.UserStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class UserStatsServiceImpl implements UserStatsService {

    private final UserStatsRepository userStatsRepository;
    private final UserRepo userRepository;

    @Override
    public UserStatsResponseDto createUserStats(UserStatsRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId());

        UserStats stats = UserStats.builder()
                .user(user)
                .totalPoints(requestDto.getTotalPoints() != null ? requestDto.getTotalPoints() : 0L)
                .totalSubmissions(requestDto.getTotalSubmissions() != null ? requestDto.getTotalSubmissions() : 0)
                .solvedProblems(requestDto.getSolvedProblems() != null ? requestDto.getSolvedProblems() : 0)
                .competitionPoints(requestDto.getCompetitionPoints() != null ? requestDto.getCompetitionPoints() : 0L)
                .habitPoints(requestDto.getHabitPoints() != null ? requestDto.getHabitPoints() : 0L)
                .lastRecalculatedAt(LocalDateTime.now())
                .build();

        userStatsRepository.save(stats);
        return mapToDto(stats);
    }

    @Override
    public UserStatsResponseDto getStatsByUserId(Long userId) {
        UserStats stats = userStatsRepository.findByUserIdNative(userId);
        if (stats == null) throw new RuntimeException("User stats not found");
        return mapToDto(stats);
    }

    @Override
    public UserStatsResponseDto updateUserStats(Long userId, UserStatsRequestDto requestDto) {
        LocalDateTime now = LocalDateTime.now();
        userStatsRepository.updateUserStatsNative(
                userId,
                requestDto.getTotalPoints(),
                requestDto.getTotalSubmissions(),
                requestDto.getSolvedProblems(),
                requestDto.getCompetitionPoints(),
                requestDto.getHabitPoints(),
                now
        );
        UserStats updatedStats = userStatsRepository.findByUserIdNative(userId);
        return mapToDto(updatedStats);
    }

    private UserStatsResponseDto mapToDto(UserStats stats) {
        return UserStatsResponseDto.builder()
                .userId(stats.getUser().getId())
                .totalPoints(stats.getTotalPoints())
                .totalSubmissions(stats.getTotalSubmissions())
                .solvedProblems(stats.getSolvedProblems())
                .competitionPoints(stats.getCompetitionPoints())
                .habitPoints(stats.getHabitPoints())
                .lastRecalculatedAt(stats.getLastRecalculatedAt())
                .build();
    }
}
