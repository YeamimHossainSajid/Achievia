package com.example.achivia.feature.score.service;
import com.example.achivia.feature.score.payload.request.ScoreEventRequestDto;
import com.example.achivia.feature.score.payload.response.ScoreEventResponseDto;

import java.util.List;
import java.util.UUID;

public interface ScoreEventService {
    ScoreEventResponseDto createScoreEvent(ScoreEventRequestDto requestDto);
    ScoreEventResponseDto getScoreEventById(UUID id);
    List<ScoreEventResponseDto> getScoreEventsByUser(Long userId);
}
