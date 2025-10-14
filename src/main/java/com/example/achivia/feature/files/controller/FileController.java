package com.example.achivia.feature.files.controller;


import com.example.achivia.feature.files.payload.request.FileRequestDto;
import com.example.achivia.feature.files.payload.response.FileResponseDto;
import com.example.achivia.feature.files.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<FileResponseDto> createFile(@RequestBody FileRequestDto dto) {
        return ResponseEntity.ok(fileService.createFile(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileResponseDto> getFileById(@PathVariable UUID id) {
        return ResponseEntity.ok(fileService.getFileById(id));
    }

    @GetMapping
    public ResponseEntity<List<FileResponseDto>> getAllFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FileResponseDto>> getFilesByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(fileService.getFilesByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable UUID id) {
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
