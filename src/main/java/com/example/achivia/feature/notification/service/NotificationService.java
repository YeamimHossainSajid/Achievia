package com.example.achivia.feature.notification.service;

import com.example.achivia.feature.notification.payload.request.NotificationRequestDto;
import com.example.achivia.feature.notification.payload.response.NotificationResponseDto;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    NotificationResponseDto create(NotificationRequestDto request);
    NotificationResponseDto getById(UUID id);
    List<NotificationResponseDto> getAll();
    List<NotificationResponseDto> getByUserId(Long userId);
    List<NotificationResponseDto> getUnreadByUserId(Long userId);
    NotificationResponseDto markAsRead(UUID id);
    void delete(UUID id);
}
