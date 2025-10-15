package com.example.achivia.feature.habitlogs.payload.request;

import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitLogRequestDto {
    private UUID habitId;
    private UUID userId;
    private LocalDate occurredOn;
    private Integer count;
    private String notes;
}
