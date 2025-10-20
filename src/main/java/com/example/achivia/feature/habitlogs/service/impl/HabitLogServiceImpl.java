package com.example.achivia.feature.habitlogs.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.habit.entity.Habit;
import com.example.achivia.feature.habit.repository.HabitRepository;
import com.example.achivia.feature.habitlogs.entity.HabitLog;
import com.example.achivia.feature.habitlogs.payload.request.HabitLogRequestDto;
import com.example.achivia.feature.habitlogs.payload.response.HabitLogResponseDto;
import com.example.achivia.feature.habitlogs.repository.HabitLogRepository;
import com.example.achivia.feature.habitlogs.service.HabitLogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HabitLogServiceImpl implements HabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;
    private final UserRepo userRepository;

    private HabitLogResponseDto convertToDto(HabitLog habitLog) {
        return HabitLogResponseDto.builder()
                .id(habitLog.getId())
                .habitId(habitLog.getHabit().getId())
                .userId(habitLog.getUser().getId())
                .occurredOn(habitLog.getOccurredOn())
                .count(habitLog.getCount())
                .notes(habitLog.getNotes())
                .createdAt(habitLog.getCreatedAt())
                .build();
    }

    private HabitLog convertToEntity(HabitLogRequestDto dto) {
        User user = userRepository.findById(dto.getUserId()).get();
        Habit habit = habitRepository.findById(dto.getHabitId())
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        return HabitLog.builder()
                .user(user)
                .habit(habit)
                .occurredOn(dto.getOccurredOn())
                .count(dto.getCount() != null ? dto.getCount() : 1)
                .notes(dto.getNotes())
                .build();
    }

    @Override
    public HabitLogResponseDto createHabitLog(HabitLogRequestDto requestDto) {
        HabitLog log = convertToEntity(requestDto);
        return convertToDto(habitLogRepository.save(log));
    }

    @Override
    public HabitLogResponseDto getHabitLogById(UUID id) {
        HabitLog log = habitLogRepository.findByIdNative(id)
                .orElseThrow(() -> new RuntimeException("HabitLog not found"));
        return convertToDto(log);
    }

    @Override
    public List<HabitLogResponseDto> getLogsByUser(UUID userId) {
        return habitLogRepository.findByUserId(userId)
                .stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HabitLogResponseDto> getLogsByHabit(UUID habitId) {
        return habitLogRepository.findByHabitId(habitId)
                .stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HabitLogResponseDto> getLogsByDate(LocalDate date) {
        return habitLogRepository.findByDate(date)
                .stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HabitLogResponseDto updateHabitLog(UUID id, HabitLogRequestDto requestDto) {
        habitLogRepository.updateHabitLog(id, requestDto.getCount(), requestDto.getNotes());
        return getHabitLogById(id);
    }

    @Override
    public void deleteHabitLog(UUID id) {
        habitLogRepository.deleteByIdNative(id);
    }
}
