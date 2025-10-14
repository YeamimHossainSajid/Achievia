package com.example.achivia.feature.guildinvitation.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GuildInvitationRequestDto {

    @NotNull(message = "Guild ID is required")
    private UUID guildId;

    @NotNull(message = "Inviter User ID is required")
    private UUID inviterUserId;

    @NotBlank(message = "Invitee email is required")
    @Email(message = "Invitee email must be valid")
    private String inviteeEmail;

    @NotNull(message = "Expiration date is required")
    private LocalDateTime expiresAt;
}
