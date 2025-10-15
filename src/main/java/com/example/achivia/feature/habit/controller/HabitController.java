package com.example.achivia.feature.habit.controller;

import com.example.achivia.feature.habit.payload.request.HabitRequestDto;
import com.example.achivia.feature.habit.payload.response.HabitResponseDto;
import com.example.achivia.feature.habit.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping
    public ResponseEntity<HabitResponseDto> createHabit(@RequestBody HabitRequestDto requestDto) {
        return ResponseEntity.ok(habitService.createHabit(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitResponseDto> getHabitById(@PathVariable UUID id) {
        return ResponseEntity.ok(habitService.getHabitById(id));
    }

    @GetMapping
    public ResponseEntity<List<HabitResponseDto>> getAllHabits() {
        return ResponseEntity.ok(habitService.getAllHabits());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HabitResponseDto>> getHabitsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(habitService.getHabitsByUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitResponseDto> updateHabit(@PathVariable UUID id, @RequestBody HabitRequestDto requestDto) {
        return ResponseEntity.ok(habitService.updateHabit(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable UUID id) {
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }
}
