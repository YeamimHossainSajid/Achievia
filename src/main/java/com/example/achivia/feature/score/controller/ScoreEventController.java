package com.example.achivia.feature.score.controller;
import com.example.achivia.feature.score.payload.request.ScoreEventRequestDto;
import com.example.achivia.feature.score.payload.response.ScoreEventResponseDto;
import com.example.achivia.feature.score.service.ScoreEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/score-events")
@RequiredArgsConstructor
public class ScoreEventController {

    private final ScoreEventService service;

    @PostMapping
    public ResponseEntity<ScoreEventResponseDto> createScoreEvent(@RequestBody ScoreEventRequestDto requestDto) {
        return ResponseEntity.ok(service.createScoreEvent(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreEventResponseDto> getScoreEventById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getScoreEventById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ScoreEventResponseDto>> getScoreEventsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getScoreEventsByUser(userId));
    }
}
