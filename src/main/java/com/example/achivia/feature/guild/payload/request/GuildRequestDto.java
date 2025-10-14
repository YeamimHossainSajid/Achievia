package com.example.achivia.feature.guild.payload.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Data
public class GuildRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Slug is required")
    @Size(max = 100)
    private String slug;

    @Size(max = 1000)
    private String description;

    @NotBlank(message = "Owner User ID is required")
    private UUID ownerUserId;

    private String iconUrl;
}
