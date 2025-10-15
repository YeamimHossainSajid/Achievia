package com.example.achivia.feature.habitlogs.payload.response;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitLogResponseDto {
    private UUID id;
    private UUID habitId;
    private UUID userId;
    private LocalDate occurredOn;
    private Integer count;
    private String notes;
    private LocalDateTime createdAt;
}
