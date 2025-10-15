package com.example.achivia.feature.notification.payload.request;

import lombok.Data;

import java.util.UUID;

@Data
public class NotificationRequestDto {
    private UUID userId;
    private String type;
    private String title;
    private String body;
}
