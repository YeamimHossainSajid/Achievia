package com.example.achivia.feature.habittemplate.service;

import com.example.achivia.feature.habittemplate.payload.request.HabitTemplateRequestDto;
import com.example.achivia.feature.habittemplate.payload.response.HabitTemplateResponseDto;

import java.util.List;
import java.util.UUID;

public interface HabitTemplateService {
    HabitTemplateResponseDto create(HabitTemplateRequestDto request);
    HabitTemplateResponseDto getById(UUID id);
    List<HabitTemplateResponseDto> getAll();
    List<HabitTemplateResponseDto> getByUserId(Long userId);
    HabitTemplateResponseDto update(UUID id, HabitTemplateRequestDto request);
    void delete(UUID id);
}
