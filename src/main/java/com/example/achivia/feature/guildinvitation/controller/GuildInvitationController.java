package com.example.achivia.feature.guildinvitation.controller;

import com.example.achivia.feature.guildinvitation.payload.request.GuildInvitationRequestDto;
import com.example.achivia.feature.guildinvitation.payload.response.GuildInvitationResponseDto;
import com.example.achivia.feature.guildinvitation.service.GuildInvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/guild-invitations")
@RequiredArgsConstructor
public class GuildInvitationController {

    private final GuildInvitationService invitationService;

    @PostMapping
    public ResponseEntity<GuildInvitationResponseDto> createInvitation(@Valid @RequestBody GuildInvitationRequestDto dto) {
        return new ResponseEntity<>(invitationService.createInvitation(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuildInvitationResponseDto> getInvitationById(@PathVariable UUID id) {
        return ResponseEntity.ok(invitationService.getInvitationById(id));
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<GuildInvitationResponseDto> getInvitationByToken(@PathVariable String token) {
        return ResponseEntity.ok(invitationService.getInvitationByToken(token));
    }

    @GetMapping("/guild/{guildId}")
    public ResponseEntity<List<GuildInvitationResponseDto>> getInvitationsByGuild(@PathVariable UUID guildId) {
        return ResponseEntity.ok(invitationService.getInvitationsByGuild(guildId));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<GuildInvitationResponseDto>> getInvitationsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(invitationService.getInvitationsByEmail(email));
    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<GuildInvitationResponseDto> acceptInvitation(@PathVariable UUID id) {
        return ResponseEntity.ok(invitationService.acceptInvitation(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable UUID id) {
        invitationService.deleteInvitation(id);
        return ResponseEntity.noContent().build();
    }
}
