package com.example.achivia.feature.habit.payload.request;

import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitRequestDto {
    private UUID userId;
    private UUID templateId;
    private String name;
    private String description;
    private String frequency;
    private Integer goal;
    private LocalDate startDate;
    private Boolean isArchived;
}
