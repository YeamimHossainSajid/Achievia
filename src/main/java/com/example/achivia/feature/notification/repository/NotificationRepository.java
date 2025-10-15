package com.example.achivia.feature.notification.repository;

import com.example.achivia.feature.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    @Query(value = "SELECT * FROM notifications", nativeQuery = true)
    List<Notification> findAllNative();

    @Query(value = "SELECT * FROM notifications WHERE id = :id", nativeQuery = true)
    Notification findByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM notifications WHERE user_id = :userId ORDER BY created_at DESC", nativeQuery = true)
    List<Notification> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM notifications WHERE user_id = :userId AND read_at IS NULL ORDER BY created_at DESC", nativeQuery = true)
    List<Notification> findUnreadByUserId(@Param("userId") Long userId);

    @Query(value = "UPDATE notifications SET read_at = NOW() WHERE id = :id RETURNING *", nativeQuery = true)
    Notification markAsRead(@Param("id") UUID id);

    @Query(value = "DELETE FROM notifications WHERE id = :id", nativeQuery = true)
    void deleteByIdNative(@Param("id") UUID id);
}
