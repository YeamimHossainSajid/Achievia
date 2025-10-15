package com.example.achivia.feature.userachievement.controller;

import com.example.achivia.feature.userachievement.payload.request.UserAchievementRequestDto;
import com.example.achivia.feature.userachievement.payload.response.UserAchievementResponseDto;
import com.example.achivia.feature.userachievement.service.UserAchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-achievements")
@RequiredArgsConstructor
public class UserAchievementController {

    private final UserAchievementService service;

    @PostMapping("/unlock")
    public ResponseEntity<UserAchievementResponseDto> unlockAchievement(@RequestBody UserAchievementRequestDto requestDto) {
        return ResponseEntity.ok(service.unlockAchievement(requestDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAchievementResponseDto>> getAchievementsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(service.getAchievementsByUser(userId));
    }

    @GetMapping("/user/{userId}/achievement/{achievementId}")
    public ResponseEntity<UserAchievementResponseDto> getUserAchievement(@PathVariable UUID userId,
                                                                         @PathVariable UUID achievementId) {
        return ResponseEntity.ok(service.getUserAchievement(userId, achievementId));
    }
}
