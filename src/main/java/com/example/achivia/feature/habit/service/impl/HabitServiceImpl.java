package com.example.achivia.feature.habit.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.habit.entity.Habit;
import com.example.achivia.feature.habit.payload.request.HabitRequestDto;
import com.example.achivia.feature.habit.payload.response.HabitResponseDto;
import com.example.achivia.feature.habit.repository.HabitRepository;
import com.example.achivia.feature.habit.service.HabitService;
import com.example.achivia.feature.habittemplate.entity.HabitTemplate;
import com.example.achivia.feature.habittemplate.repository.HabitTemplateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final UserRepo userRepository;
    private final HabitTemplateRepository habitTemplateRepository;

    private HabitResponseDto convertToDto(Habit habit) {
        return HabitResponseDto.builder()
                .id(habit.getId())
                .userId(habit.getUser() != null ? habit.getUser().getId() : null)
                .templateId(habit.getTemplate() != null ? habit.getTemplate().getId() : null)
                .name(habit.getName())
                .description(habit.getDescription())
                .frequency(habit.getFrequency())
                .goal(habit.getGoal())
                .startDate(habit.getStartDate())
                .isArchived(habit.getIsArchived())
                .createdAt(habit.getCreatedAt())
                .updatedAt(habit.getUpdatedAt())
                .build();
    }

    private Habit convertToEntity(HabitRequestDto dto) {
        User user = userRepository.findById(dto.getUserId());

        HabitTemplate template = null;
        if (dto.getTemplateId() != null) {
            template = habitTemplateRepository.findById(dto.getTemplateId())
                    .orElse(null);
        }

        return Habit.builder()
                .user(user)
                .template(template)
                .name(dto.getName())
                .description(dto.getDescription())
                .frequency(dto.getFrequency())
                .goal(dto.getGoal())
                .startDate(dto.getStartDate())
                .isArchived(dto.getIsArchived() != null ? dto.getIsArchived() : false)
                .build();
    }

    @Override
    public HabitResponseDto createHabit(HabitRequestDto requestDto) {
        Habit habit = convertToEntity(requestDto);
        return convertToDto(habitRepository.save(habit));
    }

    @Override
    public HabitResponseDto getHabitById(UUID id) {
        Habit habit = habitRepository.findByIdNative(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
        return convertToDto(habit);
    }

    @Override
    public List<HabitResponseDto> getAllHabits() {
        return habitRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HabitResponseDto> getHabitsByUser(UUID userId) {
        return habitRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HabitResponseDto updateHabit(UUID id, HabitRequestDto requestDto) {
        habitRepository.updateHabit(id, requestDto.getName(), requestDto.getDescription(),
                requestDto.getFrequency(), requestDto.getGoal(), requestDto.getIsArchived());
        return getHabitById(id);
    }

    @Override
    public void deleteHabit(UUID id) {
        habitRepository.deleteByIdNative(id);
    }
}
