package com.example.achivia.feature.guildmember.controller;

import com.example.achivia.feature.guildmember.payload.request.GuildMemberRequestDto;
import com.example.achivia.feature.guildmember.payload.response.GuildMemberResponseDto;
import com.example.achivia.feature.guildmember.service.GuildMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/guild-members")
@RequiredArgsConstructor
public class GuildMemberController {

    private final GuildMemberService memberService;

    @PostMapping
    public ResponseEntity<GuildMemberResponseDto> addMember(@Valid @RequestBody GuildMemberRequestDto dto) {
        return new ResponseEntity<>(memberService.addMember(dto), HttpStatus.CREATED);
    }

    @GetMapping("/guild/{guildId}")
    public ResponseEntity<List<GuildMemberResponseDto>> getMembersByGuild(@PathVariable UUID guildId) {
        return ResponseEntity.ok(memberService.getMembersByGuild(guildId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GuildMemberResponseDto>> getMembersByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(memberService.getMembersByUser(userId));
    }

    @GetMapping("/guild/{guildId}/user/{userId}")
    public ResponseEntity<GuildMemberResponseDto> getMember(@PathVariable UUID guildId, @PathVariable UUID userId) {
        return ResponseEntity.ok(memberService.getMember(guildId, userId));
    }

    @PatchMapping("/guild/{guildId}/user/{userId}/role")
    public ResponseEntity<GuildMemberResponseDto> updateRole(
            @PathVariable UUID guildId,
            @PathVariable UUID userId,
            @RequestParam String role
    ) {
        return ResponseEntity.ok(memberService.updateMemberRole(guildId, userId, role));
    }

    @DeleteMapping("/guild/{guildId}/user/{userId}")
    public ResponseEntity<Void> removeMember(@PathVariable UUID guildId, @PathVariable UUID userId) {
        memberService.removeMember(guildId, userId);
        return ResponseEntity.noContent().build();
    }
}
