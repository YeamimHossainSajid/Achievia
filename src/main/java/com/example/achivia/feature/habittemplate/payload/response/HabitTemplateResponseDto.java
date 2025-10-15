package com.example.achivia.feature.habittemplate.payload.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class HabitTemplateResponseDto {
    private UUID id;
    private String name;
    private String description;
    private String defaultFrequency;
    private Integer defaultGoal;
    private UUID createdByUserId;
    private LocalDateTime createdAt;
}
