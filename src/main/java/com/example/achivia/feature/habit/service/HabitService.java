package com.example.achivia.feature.habit.service;

import com.example.achivia.feature.habit.payload.request.HabitRequestDto;
import com.example.achivia.feature.habit.payload.response.HabitResponseDto;

import java.util.*;

public interface HabitService {
    HabitResponseDto createHabit(HabitRequestDto requestDto);
    HabitResponseDto getHabitById(UUID id);
    List<HabitResponseDto> getAllHabits();
    List<HabitResponseDto> getHabitsByUser(UUID userId);
    HabitResponseDto updateHabit(UUID id, HabitRequestDto requestDto);
    void deleteHabit(UUID id);
}
