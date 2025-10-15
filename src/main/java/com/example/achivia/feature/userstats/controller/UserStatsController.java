package com.example.achivia.feature.userstats.controller;

import com.example.achivia.feature.userstats.payload.request.UserStatsRequestDto;
import com.example.achivia.feature.userstats.payload.response.UserStatsResponseDto;
import com.example.achivia.feature.userstats.service.UserStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-stats")
@RequiredArgsConstructor
public class UserStatsController {

    private final UserStatsService service;

    @PostMapping
    public ResponseEntity<UserStatsResponseDto> createStats(@RequestBody UserStatsRequestDto requestDto) {
        return ResponseEntity.ok(service.createUserStats(requestDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserStatsResponseDto> getStats(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getStatsByUserId(userId));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserStatsResponseDto> updateStats(@PathVariable Long userId,
                                                            @RequestBody UserStatsRequestDto requestDto) {
        return ResponseEntity.ok(service.updateUserStats(userId, requestDto));
    }
}
