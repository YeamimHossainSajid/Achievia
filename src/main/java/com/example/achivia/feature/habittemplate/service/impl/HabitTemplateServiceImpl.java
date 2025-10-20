package com.example.achivia.feature.habittemplate.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.habittemplate.entity.HabitTemplate;
import com.example.achivia.feature.habittemplate.payload.request.HabitTemplateRequestDto;
import com.example.achivia.feature.habittemplate.payload.response.HabitTemplateResponseDto;
import com.example.achivia.feature.habittemplate.repository.HabitTemplateRepository;
import com.example.achivia.feature.habittemplate.service.HabitTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HabitTemplateServiceImpl implements HabitTemplateService {

    private final HabitTemplateRepository habitTemplateRepository;
    private final UserRepo userRepository;

    @Override
    public HabitTemplateResponseDto create(HabitTemplateRequestDto request) {
        User user = userRepository.findById(request.getCreatedByUserId()).get();

        HabitTemplate template = convertToEntity(request, user);
        HabitTemplate saved = habitTemplateRepository.save(template);
        return convertToDto(saved);
    }

    @Override
    public HabitTemplateResponseDto getById(UUID id) {
        HabitTemplate template = habitTemplateRepository.findByIdNative(id);
        if (template == null) throw new RuntimeException("HabitTemplate not found");
        return convertToDto(template);
    }

    @Override
    public List<HabitTemplateResponseDto> getAll() {
        return habitTemplateRepository.findAllTemplates()
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<HabitTemplateResponseDto> getByUserId(Long userId) {
        return habitTemplateRepository.findByUserId(userId)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public HabitTemplateResponseDto update(UUID id, HabitTemplateRequestDto request) {
        HabitTemplate updated = habitTemplateRepository.updateTemplate(
                id,
                request.getName(),
                request.getDescription(),
                request.getDefaultFrequency(),
                request.getDefaultGoal()
        );
        return convertToDto(updated);
    }

    @Override
    public void delete(UUID id) {
        habitTemplateRepository.deleteByIdNative(id);
    }

    private HabitTemplate convertToEntity(HabitTemplateRequestDto dto, User user) {
        return HabitTemplate.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .defaultFrequency(dto.getDefaultFrequency())
                .defaultGoal(dto.getDefaultGoal())
                .createdByUser(user)
                .build();
    }

    private HabitTemplateResponseDto convertToDto(HabitTemplate entity) {
        HabitTemplateResponseDto dto = new HabitTemplateResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setDefaultFrequency(entity.getDefaultFrequency());
        dto.setDefaultGoal(entity.getDefaultGoal());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setCreatedByUserId(entity.getCreatedByUser() != null ? entity.getCreatedByUser().getId() : null);
        return dto;
    }
}
