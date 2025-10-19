package com.example.achivia.feature.blog.controller;

import com.example.achivia.feature.blog.payload.request.BlogRequestDto;
import com.example.achivia.feature.blog.payload.response.BlogResponseDto;
import com.example.achivia.feature.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService service;

    @PostMapping
    public ResponseEntity<BlogResponseDto> createBlog(@RequestBody BlogRequestDto requestDto) {
        return ResponseEntity.ok(service.createBlog(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponseDto> updateBlog(@PathVariable UUID id, @RequestBody BlogRequestDto requestDto) {
        return ResponseEntity.ok(service.updateBlog(id, requestDto));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BlogResponseDto>> getBlogsByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(service.getBlogsByAuthor(authorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponseDto> getBlogById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getBlogById(id));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<BlogResponseDto> getBlogBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(service.getBlogBySlug(slug));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable UUID id) {
        service.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
