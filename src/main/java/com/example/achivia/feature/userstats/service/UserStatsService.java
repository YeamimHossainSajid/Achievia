package com.example.achivia.feature.userstats.service;

import com.example.achivia.feature.userstats.payload.request.UserStatsRequestDto;
import com.example.achivia.feature.userstats.payload.response.UserStatsResponseDto;

public interface UserStatsService {
    UserStatsResponseDto createUserStats(UserStatsRequestDto requestDto);
    UserStatsResponseDto getStatsByUserId(Long userId);
    UserStatsResponseDto updateUserStats(Long userId, UserStatsRequestDto requestDto);
}
