package com.example.achivia.feature.habittemplate.controller;

import com.example.achivia.feature.habittemplate.payload.request.HabitTemplateRequestDto;
import com.example.achivia.feature.habittemplate.payload.response.HabitTemplateResponseDto;
import com.example.achivia.feature.habittemplate.service.HabitTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/habit-templates")
@RequiredArgsConstructor
public class HabitTemplateController {

    private final HabitTemplateService habitTemplateService;

    @PostMapping
    public ResponseEntity<HabitTemplateResponseDto> create(@RequestBody HabitTemplateRequestDto request) {
        return ResponseEntity.ok(habitTemplateService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitTemplateResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(habitTemplateService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<HabitTemplateResponseDto>> getAll() {
        return ResponseEntity.ok(habitTemplateService.getAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HabitTemplateResponseDto>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(habitTemplateService.getByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitTemplateResponseDto> update(@PathVariable UUID id, @RequestBody HabitTemplateRequestDto request) {
        return ResponseEntity.ok(habitTemplateService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        habitTemplateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
