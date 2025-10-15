package com.example.achivia.feature.submission.service;

import com.example.achivia.feature.submission.payload.request.SubmissionRequestDto;
import com.example.achivia.feature.submission.payload.response.SubmissionResponseDto;

import java.util.List;
import java.util.UUID;

public interface SubmissionService {
    SubmissionResponseDto createSubmission(SubmissionRequestDto requestDto);
    SubmissionResponseDto getSubmissionById(UUID id);
    List<SubmissionResponseDto> getSubmissionsByUser(UUID userId);
}
