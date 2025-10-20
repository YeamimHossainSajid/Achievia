package com.example.achivia.feature.notification.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.notification.entity.Notification;
import com.example.achivia.feature.notification.payload.request.NotificationRequestDto;
import com.example.achivia.feature.notification.payload.response.NotificationResponseDto;
import com.example.achivia.feature.notification.repository.NotificationRepository;
import com.example.achivia.feature.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepo userRepository;

    @Override
    public NotificationResponseDto create(NotificationRequestDto request) {
        User user = userRepository.findById(request.getUserId()).get();

        Notification notification = convertToEntity(request, user);
        Notification saved = notificationRepository.save(notification);
        return convertToDto(saved);
    }

    @Override
    public NotificationResponseDto getById(UUID id) {
        Notification notification = notificationRepository.findByIdNative(id);
        if (notification == null) throw new RuntimeException("Notification not found");
        return convertToDto(notification);
    }

    @Override
    public List<NotificationResponseDto> getAll() {
        return notificationRepository.findAllNative()
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponseDto> getByUserId(Long userId) {
        return notificationRepository.findByUserId(userId)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponseDto> getUnreadByUserId(Long userId) {
        return notificationRepository.findUnreadByUserId(userId)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public NotificationResponseDto markAsRead(UUID id) {
        Notification updated = notificationRepository.markAsRead(id);
        return convertToDto(updated);
    }

    @Override
    public void delete(UUID id) {
        notificationRepository.deleteByIdNative(id);
    }

    private Notification convertToEntity(NotificationRequestDto dto, User user) {
        return Notification.builder()
                .user(user)
                .type(dto.getType())
                .title(dto.getTitle())
                .body(dto.getBody())
                .build();
    }

    private NotificationResponseDto convertToDto(Notification entity) {
        NotificationResponseDto dto = new NotificationResponseDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setType(entity.getType());
        dto.setTitle(entity.getTitle());
        dto.setBody(entity.getBody());
        dto.setReadAt(entity.getReadAt());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
