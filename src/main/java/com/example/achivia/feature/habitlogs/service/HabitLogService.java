package com.example.achivia.feature.habitlogs.service;


import com.example.achivia.feature.habitlogs.payload.request.HabitLogRequestDto;
import com.example.achivia.feature.habitlogs.payload.response.HabitLogResponseDto;

import java.time.LocalDate;
import java.util.*;

public interface HabitLogService {
    HabitLogResponseDto createHabitLog(HabitLogRequestDto requestDto);
    HabitLogResponseDto getHabitLogById(UUID id);
    List<HabitLogResponseDto> getLogsByUser(UUID userId);
    List<HabitLogResponseDto> getLogsByHabit(UUID habitId);
    List<HabitLogResponseDto> getLogsByDate(LocalDate date);
    HabitLogResponseDto updateHabitLog(UUID id, HabitLogRequestDto requestDto);
    void deleteHabitLog(UUID id);
}
