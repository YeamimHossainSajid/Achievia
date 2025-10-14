package com.example.achivia.feature.guild.controller;

import com.example.achivia.feature.guild.payload.request.GuildRequestDto;
import com.example.achivia.feature.guild.payload.response.GuildResponseDto;
import com.example.achivia.feature.guild.service.GuildService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/guilds")
@RequiredArgsConstructor
public class GuildController {

    private final GuildService guildService;

    @PostMapping
    public ResponseEntity<GuildResponseDto> createGuild(@Valid @RequestBody GuildRequestDto dto) {
        return new ResponseEntity<>(guildService.createGuild(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuildResponseDto> getGuildById(@PathVariable UUID id) {
        return ResponseEntity.ok(guildService.getGuildById(id));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<GuildResponseDto> getGuildBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(guildService.getGuildBySlug(slug));
    }

    @GetMapping("/owner/{ownerUserId}")
    public ResponseEntity<List<GuildResponseDto>> getGuildsByOwner(@PathVariable Long ownerUserId) {
        return ResponseEntity.ok(guildService.getGuildsByOwner(ownerUserId));
    }

    @GetMapping
    public ResponseEntity<List<GuildResponseDto>> getAllGuilds() {
        return ResponseEntity.ok(guildService.getAllGuilds());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuildResponseDto> updateGuild(@PathVariable UUID id,
                                                        @Valid @RequestBody GuildRequestDto dto) {
        return ResponseEntity.ok(guildService.updateGuild(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuild(@PathVariable UUID id) {
        guildService.deleteGuild(id);
        return ResponseEntity.noContent().build();
    }
}
