package com.sajid.Achievia.feature.badges.controller;

import com.sajid.Achievia.feature.badges.payload.request.BadgeRequestDto;
import com.sajid.Achievia.feature.badges.payload.response.BadgeResponseDto;
import com.sajid.Achievia.feature.badges.service.BadgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {

    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody BadgeRequestDto badgeRequestDto) {
        badgeService.create(badgeRequestDto);
        return ResponseEntity.ok("Badge created successfully!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BadgeResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(badgeService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<BadgeResponseDto>> getAll() {
        return ResponseEntity.ok(badgeService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody BadgeRequestDto badgeRequestDto) {
        badgeService.update(badgeRequestDto, id);
        return ResponseEntity.ok("Badge updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        badgeService.delete(id);
        return ResponseEntity.ok("Badge deleted successfully!");
    }
}

