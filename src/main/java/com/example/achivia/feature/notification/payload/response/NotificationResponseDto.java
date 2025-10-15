package com.example.achivia.feature.notification.payload.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificationResponseDto {
    private UUID id;
    private UUID userId;
    private String type;
    private String title;
    private String body;
    private LocalDateTime readAt;
    private LocalDateTime createdAt;
}
