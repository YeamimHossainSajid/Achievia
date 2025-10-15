package com.example.achivia.feature.problemtest.service;

import com.example.achivia.feature.problemtest.payload.request.ProblemTestRequestDto;
import com.example.achivia.feature.problemtest.payload.response.ProblemTestResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProblemTestService {
    ProblemTestResponseDto createProblemTest(ProblemTestRequestDto dto);
    List<ProblemTestResponseDto> getTestsByProblem(UUID problemId);
    List<ProblemTestResponseDto> getSampleTests(UUID problemId);
}
