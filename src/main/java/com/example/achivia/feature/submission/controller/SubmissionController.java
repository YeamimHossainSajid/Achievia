package com.example.achivia.feature.submission.controller;

import com.example.achivia.feature.submission.payload.request.SubmissionRequestDto;
import com.example.achivia.feature.submission.payload.response.SubmissionResponseDto;
import com.example.achivia.feature.submission.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService service;

    @PostMapping
    public ResponseEntity<SubmissionResponseDto> createSubmission(@RequestBody SubmissionRequestDto requestDto) {
        return ResponseEntity.ok(service.createSubmission(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmissionResponseDto> getSubmissionById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getSubmissionById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SubmissionResponseDto>> getSubmissionsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(service.getSubmissionsByUser(userId));
    }
}
