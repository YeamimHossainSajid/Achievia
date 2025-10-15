package com.example.achivia.feature.notification.controller;

import com.example.achivia.feature.notification.payload.request.NotificationRequestDto;
import com.example.achivia.feature.notification.payload.response.NotificationResponseDto;
import com.example.achivia.feature.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponseDto> create(@RequestBody NotificationRequestDto request) {
        return ResponseEntity.ok(notificationService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(notificationService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseDto>> getAll() {
        return ResponseEntity.ok(notificationService.getAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponseDto>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getByUserId(userId));
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<NotificationResponseDto>> getUnreadByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUnreadByUserId(userId));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<NotificationResponseDto> markAsRead(@PathVariable UUID id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
