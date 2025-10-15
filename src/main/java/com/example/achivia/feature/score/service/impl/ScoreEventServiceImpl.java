package com.example.achivia.feature.score.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.score.entity.ScoreEvent;
import com.example.achivia.feature.score.payload.request.ScoreEventRequestDto;
import com.example.achivia.feature.score.payload.response.ScoreEventResponseDto;
import com.example.achivia.feature.score.repository.ScoreEventRepository;
import com.example.achivia.feature.score.service.ScoreEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ScoreEventServiceImpl implements ScoreEventService {

    private final ScoreEventRepository repository;
    private final UserRepo userRepository;

    @Override
    public ScoreEventResponseDto createScoreEvent(ScoreEventRequestDto requestDto) {
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        User user = userRepository.findById(requestDto.getUserId());

        repository.insertScoreEvent(
                id,
                requestDto.getUserId(),
                requestDto.getSource(),
                requestDto.getSourceId(),
                requestDto.getPoints(),
                requestDto.getMetadata(),
                now,
                now
        );

        ScoreEvent scoreEvent = repository.findByIdNative(id)
                .orElseThrow(() -> new RuntimeException("Failed to fetch inserted score event"));

        return mapToDto(scoreEvent);
    }

    @Override
    public ScoreEventResponseDto getScoreEventById(UUID id) {
        ScoreEvent scoreEvent = repository.findByIdNative(id)
                .orElseThrow(() -> new RuntimeException("ScoreEvent not found"));
        return mapToDto(scoreEvent);
    }

    @Override
    public List<ScoreEventResponseDto> getScoreEventsByUser(Long userId) {
        List<ScoreEvent> events = repository.findByUserId(userId);
        return events.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private ScoreEventResponseDto mapToDto(ScoreEvent scoreEvent) {
        return ScoreEventResponseDto.builder()
                .id(scoreEvent.getId())
                .userId(scoreEvent.getUser().getId())
                .source(scoreEvent.getSource())
                .sourceId(scoreEvent.getSourceId())
                .points(scoreEvent.getPoints())
                .metadata(scoreEvent.getMetadata())
                .occurredAt(scoreEvent.getOccurredAt())
                .createdAt(scoreEvent.getCreatedAt())
                .build();
    }
}
