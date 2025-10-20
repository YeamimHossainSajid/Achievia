package com.example.achivia.feature.notification.repository;

import com.example.achivia.feature.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO notification (id, user_id, type, title, body, created_at)
        VALUES (:id, :userId, :type, :title, :body, NOW())
        ON CONFLICT (id) DO UPDATE
        SET type = :type,
            title = :title,
            body = :body
        """, nativeQuery = true)
    Notification save(
            @Param("id") UUID id,
            @Param("userId") Long userId,
            @Param("type") String type,
            @Param("title") String title,
            @Param("body") String body
    );

    @Query(value = """
        SELECT * FROM notification
        WHERE id = :id
        """, nativeQuery = true)
    Notification findByIdNative(@Param("id") UUID id);

    @Query(value = """
        SELECT * FROM notification
        """, nativeQuery = true)
    List<Notification> findAllNative();

    @Query(value = """
        SELECT * FROM notification
        WHERE user_id = :userId
        """, nativeQuery = true)
    List<Notification> findByUserId(@Param("userId") Long userId);

    @Query(value = """
        SELECT * FROM notification
        WHERE user_id = :userId AND read_at IS NULL
        """, nativeQuery = true)
    List<Notification> findUnreadByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE notification
        SET read_at = NOW()
        WHERE id = :id
        """, nativeQuery = true)
    Notification markAsRead(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM notification
        WHERE id = :id
        """, nativeQuery = true)
    void deleteByIdNative(@Param("id") UUID id);
}
