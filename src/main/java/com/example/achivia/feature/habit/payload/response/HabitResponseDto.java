package com.example.achivia.feature.habit.payload.response;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitResponseDto {
    private UUID id;
    private UUID userId;
    private UUID templateId;
    private String name;
    private String description;
    private String frequency;
    private Integer goal;
    private LocalDate startDate;
    private Boolean isArchived;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
