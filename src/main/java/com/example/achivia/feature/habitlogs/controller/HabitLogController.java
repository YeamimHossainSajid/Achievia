package com.example.achivia.feature.habitlogs.controller;

import com.example.achivia.feature.habitlogs.payload.request.HabitLogRequestDto;
import com.example.achivia.feature.habitlogs.payload.response.HabitLogResponseDto;
import com.example.achivia.feature.habitlogs.service.HabitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/habit-logs")
@RequiredArgsConstructor
public class HabitLogController {

    private final HabitLogService habitLogService;

    @PostMapping
    public ResponseEntity<HabitLogResponseDto> createHabitLog(@RequestBody HabitLogRequestDto requestDto) {
        return ResponseEntity.ok(habitLogService.createHabitLog(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitLogResponseDto> getHabitLogById(@PathVariable UUID id) {
        return ResponseEntity.ok(habitLogService.getHabitLogById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HabitLogResponseDto>> getLogsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(habitLogService.getLogsByUser(userId));
    }

    @GetMapping("/habit/{habitId}")
    public ResponseEntity<List<HabitLogResponseDto>> getLogsByHabit(@PathVariable UUID habitId) {
        return ResponseEntity.ok(habitLogService.getLogsByHabit(habitId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<HabitLogResponseDto>> getLogsByDate(@PathVariable LocalDate date) {
        return ResponseEntity.ok(habitLogService.getLogsByDate(date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitLogResponseDto> updateHabitLog(@PathVariable UUID id, @RequestBody HabitLogRequestDto requestDto) {
        return ResponseEntity.ok(habitLogService.updateHabitLog(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitLog(@PathVariable UUID id) {
        habitLogService.deleteHabitLog(id);
        return ResponseEntity.noContent().build();
    }
}
