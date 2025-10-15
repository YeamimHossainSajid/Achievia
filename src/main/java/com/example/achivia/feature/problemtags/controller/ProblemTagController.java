package com.example.achivia.feature.problemtags.controller;
import com.example.achivia.feature.problemtags.payload.request.ProblemTagRequestDto;
import com.example.achivia.feature.problemtags.payload.response.ProblemTagResponseDto;
import com.example.achivia.feature.problemtags.service.ProblemTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/problem-tags")
@RequiredArgsConstructor
public class ProblemTagController {

    private final ProblemTagService problemTagService;

    @PostMapping
    public ResponseEntity<ProblemTagResponseDto> assignTagToProblem(@RequestBody ProblemTagRequestDto dto) {
        return ResponseEntity.ok(problemTagService.assignTagToProblem(dto));
    }

    @GetMapping("/problem/{problemId}")
    public ResponseEntity<List<ProblemTagResponseDto>> getTagsByProblem(@PathVariable UUID problemId) {
        return ResponseEntity.ok(problemTagService.getTagsByProblem(problemId));
    }

    @GetMapping("/tag/{tagId}")
    public ResponseEntity<List<ProblemTagResponseDto>> getProblemsByTag(@PathVariable UUID tagId) {
        return ResponseEntity.ok(problemTagService.getProblemsByTag(tagId));
    }
}
