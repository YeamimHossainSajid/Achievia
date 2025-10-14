package com.example.achivia.feature.guildmember.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class GuildMemberRequestDto {

    @NotNull(message = "Guild ID is required")
    private UUID guildId;

    @NotNull(message = "User ID is required")
    private UUID userId;

    private String role;
}
