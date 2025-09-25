package com.sajid.Achievia.feature.guilds.controller;

import com.sajid.Achievia.feature.guilds.payload.request.GuildRequestDto;
import com.sajid.Achievia.feature.guilds.payload.response.GuildResponseDto;
import com.sajid.Achievia.feature.guilds.service.GuildService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guilds")
public class GuildController {

    private final GuildService guildService;

    public GuildController(GuildService guildService) {
        this.guildService = guildService;
    }

    @PostMapping
    public ResponseEntity<String> createGuild(@RequestBody GuildRequestDto guildRequestDto) {
        guildService.createGuild(guildRequestDto);
        return ResponseEntity.ok("Guild created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGuild(@PathVariable Long id, @RequestBody GuildRequestDto guildRequestDto) {
        guildService.updateGuild(id,guildRequestDto);
        return ResponseEntity.ok("Guild updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuild(@PathVariable Long id) {
        guildService.deleteGuild(id);
        return ResponseEntity.ok("Guild deleted successfully!");
    }

    @GetMapping
    public ResponseEntity<List<GuildResponseDto>> getAllGuilds() {
        return ResponseEntity.ok(guildService.getGuilds());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuildResponseDto> getGuildById(@PathVariable Long id) {
        return ResponseEntity.ok(guildService.getGuildById(id));
    }
}

