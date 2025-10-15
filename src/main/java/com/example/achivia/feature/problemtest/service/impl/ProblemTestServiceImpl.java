package com.example.achivia.feature.problemtest.service.impl;

import com.example.achivia.feature.problem.entity.Problem;
import com.example.achivia.feature.problem.repositroy.ProblemRepository;
import com.example.achivia.feature.problemtest.entity.ProblemTest;
import com.example.achivia.feature.problemtest.payload.request.ProblemTestRequestDto;
import com.example.achivia.feature.problemtest.payload.response.ProblemTestResponseDto;
import com.example.achivia.feature.problemtest.repository.ProblemTestRepository;
import com.example.achivia.feature.problemtest.service.ProblemTestService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProblemTestServiceImpl implements ProblemTestService {

    private final ProblemTestRepository problemTestRepository;
    private final ProblemRepository problemRepository;

    @Override
    public ProblemTestResponseDto createProblemTest(ProblemTestRequestDto dto) {
        Problem problem = problemRepository.findById(dto.getProblemId())
                .orElseThrow(() -> new EntityNotFoundException("Problem not found with ID: " + dto.getProblemId()));

        ProblemTest test = ProblemTest.builder()
                .problem(problem)
                .isSample(dto.getIsSample() != null ? dto.getIsSample() : false)
                .input(dto.getInput())
                .expectedOutput(dto.getExpectedOutput())
                .scoreWeight(dto.getScoreWeight() != null ? dto.getScoreWeight() : 1)
                .build();

        ProblemTest saved = problemTestRepository.save(test);
        return mapToResponse(saved);
    }

    @Override
    public List<ProblemTestResponseDto> getTestsByProblem(UUID problemId) {
        return problemTestRepository.findByProblemId(problemId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProblemTestResponseDto> getSampleTests(UUID problemId) {
        return problemTestRepository.findSampleTestsByProblemId(problemId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ProblemTestResponseDto mapToResponse(ProblemTest entity) {
        return ProblemTestResponseDto.builder()
                .id(entity.getId())
                .problemId(entity.getProblem().getId())
                .problemTitle(entity.getProblem().getTitle())
                .isSample(entity.getIsSample())
                .input(entity.getInput())
                .expectedOutput(entity.getExpectedOutput())
                .scoreWeight(entity.getScoreWeight())
                .build();
    }
}
