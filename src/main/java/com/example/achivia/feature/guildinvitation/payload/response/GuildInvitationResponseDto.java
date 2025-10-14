package com.example.achivia.feature.guildinvitation.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class GuildInvitationResponseDto {

    private UUID id;
    private UUID guildId;
    private UUID inviterUserId;
    private String inviteeEmail;
    private String token;
    private LocalDateTime expiresAt;
    private LocalDateTime acceptedAt;
}
