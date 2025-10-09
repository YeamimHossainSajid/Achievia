package com.example.achivia.feature.achievement.controller;

import com.example.achivia.feature.achievement.payload.request.AchievementRequestDto;
import com.example.achivia.feature.achievement.payload.response.AchievementResponseDto;
import com.example.achivia.feature.achievement.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/achievements")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService achievementService;

    @GetMapping
    public ResponseEntity<List<AchievementResponseDto>> getAllAchievements() {
        List<AchievementResponseDto> achievements = achievementService.getAchievements();
        return ResponseEntity.ok(achievements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AchievementResponseDto> getAchievementById(@PathVariable UUID id) {
        AchievementResponseDto achievement = achievementService.getAchievementById(id);
        return ResponseEntity.ok(achievement);
    }

    @PostMapping
    public ResponseEntity<AchievementResponseDto> createAchievement(
            @RequestBody AchievementRequestDto requestDto) {
        AchievementResponseDto created = achievementService.insertAchievement(requestDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AchievementResponseDto> updateAchievement(
            @PathVariable UUID id,
            @RequestBody AchievementRequestDto requestDto) {
        AchievementResponseDto updated = achievementService.updateAchievement(id, requestDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchievement(@PathVariable UUID id) {
        achievementService.deleteAchievement(id);
        return ResponseEntity.noContent().build();
    }

}
