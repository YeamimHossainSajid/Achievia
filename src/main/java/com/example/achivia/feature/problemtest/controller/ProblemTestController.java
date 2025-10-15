package com.example.achivia.feature.problemtest.controller;
import com.example.achivia.feature.problemtest.payload.request.ProblemTestRequestDto;
import com.example.achivia.feature.problemtest.payload.response.ProblemTestResponseDto;
import com.example.achivia.feature.problemtest.service.ProblemTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/problem-tests")
@RequiredArgsConstructor
public class ProblemTestController {

    private final ProblemTestService problemTestService;

    @PostMapping
    public ResponseEntity<ProblemTestResponseDto> createProblemTest(@RequestBody ProblemTestRequestDto dto) {
        return ResponseEntity.ok(problemTestService.createProblemTest(dto));
    }

    @GetMapping("/problem/{problemId}")
    public ResponseEntity<List<ProblemTestResponseDto>> getTestsByProblem(@PathVariable UUID problemId) {
        return ResponseEntity.ok(problemTestService.getTestsByProblem(problemId));
    }

    @GetMapping("/problem/{problemId}/samples")
    public ResponseEntity<List<ProblemTestResponseDto>> getSampleTests(@PathVariable UUID problemId) {
        return ResponseEntity.ok(problemTestService.getSampleTests(problemId));
    }
}
