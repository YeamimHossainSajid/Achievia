package com.example.achivia.feature.guild.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class GuildResponseDto {

    private UUID id;
    private String name;
    private String slug;
    private String description;
    private UUID ownerUserId;
    private String iconUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
