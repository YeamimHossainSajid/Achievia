package com.example.achivia.feature.problemtags.service;

import com.example.achivia.feature.problemtags.payload.request.ProblemTagRequestDto;
import com.example.achivia.feature.problemtags.payload.response.ProblemTagResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProblemTagService {
    ProblemTagResponseDto assignTagToProblem(ProblemTagRequestDto dto);
    List<ProblemTagResponseDto> getTagsByProblem(UUID problemId);
    List<ProblemTagResponseDto> getProblemsByTag(UUID tagId);
}
