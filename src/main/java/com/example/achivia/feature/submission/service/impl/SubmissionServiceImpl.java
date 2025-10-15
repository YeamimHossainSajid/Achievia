package com.example.achivia.feature.submission.service.impl;

import com.example.achivia.feature.submission.entity.Submission;
import com.example.achivia.feature.submission.payload.request.SubmissionRequestDto;
import com.example.achivia.feature.submission.payload.response.SubmissionResponseDto;
import com.example.achivia.feature.submission.repository.SubmissionRepository;
import com.example.achivia.feature.submission.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository repository;

    @Override
    public SubmissionResponseDto createSubmission(SubmissionRequestDto requestDto) {
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        repository.insertSubmission(
                id,
                requestDto.getUserId(),
                requestDto.getProblemId(),
                requestDto.getCompetitionId(),
                requestDto.getLanguage(),
                requestDto.getCode(),
                requestDto.getStatus(),
                requestDto.getScore() != null ? requestDto.getScore() : 0,
                requestDto.getTimeMs(),
                requestDto.getMemoryKb(),
                requestDto.getErrorMessage(),
                now,
                now
        );

        Submission submission = repository.findByIdNative(id)
                .orElseThrow(() -> new RuntimeException("Failed to fetch inserted Submission"));

        return mapToDto(submission);
    }

    @Override
    public SubmissionResponseDto getSubmissionById(UUID id) {
        Submission submission = repository.findByIdNative(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
        return mapToDto(submission);
    }

    @Override
    public List<SubmissionResponseDto> getSubmissionsByUser(UUID userId) {
        List<Submission> submissions = repository.findByUserIdNative(userId);
        return submissions.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private SubmissionResponseDto mapToDto(Submission submission) {
        return SubmissionResponseDto.builder()
                .id(submission.getId())
                .userId(submission.getUser().getId())
                .problemId(submission.getProblem().getId())
                .competitionId(submission.getCompetition() != null ? submission.getCompetition().getId() : null)
                .language(submission.getLanguage())
                .code(submission.getCode())
                .status(submission.getStatus())
                .score(submission.getScore())
                .timeMs(submission.getTimeMs())
                .memoryKb(submission.getMemoryKb())
                .errorMessage(submission.getErrorMessage())
                .createdAt(submission.getCreatedAt())
                .updatedAt(submission.getUpdatedAt())
                .build();
    }
}
