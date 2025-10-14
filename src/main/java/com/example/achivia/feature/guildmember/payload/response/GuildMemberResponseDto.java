package com.example.achivia.feature.guildmember.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class GuildMemberResponseDto {

    private UUID guildId;
    private UUID userId;
    private String role;
    private LocalDateTime joinedAt;
}
