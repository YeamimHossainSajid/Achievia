package com.example.achivia.feature.habittemplate.payload.request;

import lombok.Data;
import java.util.UUID;

@Data
public class HabitTemplateRequestDto {
    private String name;
    private String description;
    private String defaultFrequency;
    private Integer defaultGoal;
    private UUID createdByUserId;
}
