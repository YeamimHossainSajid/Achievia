package com.example.achivia.feature.achievement.service.impl;

import com.example.achivia.feature.achievement.entity.Achievement;
import com.example.achivia.feature.achievement.payload.request.AchievementRequestDto;
import com.example.achivia.feature.achievement.payload.response.AchievementResponseDto;
import com.example.achivia.feature.achievement.repository.AchievementRepository;
import com.example.achivia.feature.achievement.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository repository;

    public AchievementServiceImpl(AchievementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AchievementResponseDto> getAchievements() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AchievementResponseDto getAchievementById(UUID id) {
        Achievement achievement = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found with id: " + id));
        return mapToDto(achievement);
    }

    @Override
    public AchievementResponseDto insertAchievement(AchievementRequestDto dto) {
        Achievement achievement = new Achievement();

        achievement.setName(dto.getName());
        achievement.setDescription(dto.getDescription());
        achievement.setIconUrl(dto.getIconUrl());
        achievement.setPointsReward(dto.getPointsReward() != null ? dto.getPointsReward() : 0);

        achievement.setSlug(generateUniqueSlug(dto.getName()));

        Achievement saved = repository.save(achievement);
        return mapToDto(saved);
    }

    @Override
    public AchievementResponseDto updateAchievement(UUID id, AchievementRequestDto dto) {
        Achievement achievement = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found with id: " + id));

        achievement.setName(dto.getName());
        achievement.setDescription(dto.getDescription());
        achievement.setIconUrl(dto.getIconUrl());
        achievement.setPointsReward(dto.getPointsReward() != null ? dto.getPointsReward() : achievement.getPointsReward());

        if (!achievement.getName().equals(dto.getName())) {
            achievement.setSlug(generateUniqueSlug(dto.getName()));
        }

        Achievement updated = repository.save(achievement);
        return mapToDto(updated);
    }

    @Override
    public void deleteAchievement(UUID id) {
        Achievement achievement = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found with id: " + id));
        repository.delete(achievement);
    }

    private AchievementResponseDto mapToDto(Achievement achievement) {
        AchievementResponseDto dto = new AchievementResponseDto();
        dto.setId(achievement.getId());
        dto.setSlug(achievement.getSlug());
        dto.setName(achievement.getName());
        dto.setDescription(achievement.getDescription());
        dto.setIconUrl(achievement.getIconUrl());
        dto.setPointsReward(achievement.getPointsReward());
        dto.setCreatedAt(achievement.getCreatedAt());
        return dto;
    }

    private String generateUniqueSlug(String name) {
        String baseSlug = name.toLowerCase().trim().replaceAll("[^a-z0-9]+", "-");
        String slug = baseSlug;
        int counter = 1;

        while (repository.existsBySlug(slug)) {
            slug = baseSlug + "-" + counter;
            counter++;
        }

        return slug;
    }
}
