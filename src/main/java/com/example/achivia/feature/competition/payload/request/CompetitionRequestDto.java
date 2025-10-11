package com.example.achivia.feature.competition.payload.request;


import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompetitionRequestDto {

    @NotBlank(message = "Slug is required")
    @Size(max = 100, message = "Slug must not exceed 100 characters")
    private String slug;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    @NotNull(message = "Start date is required")
    private LocalDateTime startAt;

    @NotNull(message = "End date is required")
    private LocalDateTime endAt;

    @Pattern(regexp = "public|private", message = "Visibility must be 'public' or 'private'")
    private String visibility = "public";

    @NotNull(message = "Host user ID is required")
    private UUID hostUserId;
}
