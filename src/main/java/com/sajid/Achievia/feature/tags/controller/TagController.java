package com.sajid.Achievia.feature.tags.controller;

import com.sajid.Achievia.feature.tags.payload.request.TagRequestDto;
import com.sajid.Achievia.feature.tags.payload.response.TagResponseDto;
import com.sajid.Achievia.feature.tags.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagResponseDto>> getAllTags() {
        return ResponseEntity.ok(tagService.getTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDto> getTagById(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.getTagById(id));
    }

    @PostMapping
    public ResponseEntity<String> createTag(@RequestBody TagRequestDto tagRequestDto) {
        tagService.createTag(tagRequestDto);
        return ResponseEntity.ok("Tag created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTag(@PathVariable Long id, @RequestBody TagRequestDto tagRequestDto) {
        tagService.updateTag(id, tagRequestDto);
        return ResponseEntity.ok("Tag updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok("Tag deleted successfully!");
    }
}

