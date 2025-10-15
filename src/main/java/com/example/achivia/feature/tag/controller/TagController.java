package com.example.achivia.feature.tag.controller;

import com.example.achivia.feature.tag.payload.request.TagRequestDto;
import com.example.achivia.feature.tag.payload.response.TagResponseDto;
import com.example.achivia.feature.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService service;

    @PostMapping
    public ResponseEntity<TagResponseDto> createTag(@RequestBody TagRequestDto requestDto) {
        return ResponseEntity.ok(service.createTag(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDto> getTagById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getTagById(id));
    }

    @GetMapping
    public ResponseEntity<List<TagResponseDto>> getAllTags() {
        return ResponseEntity.ok(service.getAllTags());
    }
}
