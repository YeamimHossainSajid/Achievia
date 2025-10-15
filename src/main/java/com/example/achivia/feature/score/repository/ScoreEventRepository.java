package com.example.achivia.feature.score.repository;

import com.example.achivia.feature.score.entity.ScoreEvent;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScoreEventRepository extends JpaRepository<ScoreEvent, UUID> {

    @Query(value = "SELECT * FROM score_events WHERE user_id = :userId ORDER BY occurred_at DESC", nativeQuery = true)
    List<ScoreEvent> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM score_events WHERE id = :id", nativeQuery = true)
    Optional<ScoreEvent> findByIdNative(@Param("id") UUID id);

    @Modifying
    @Query(value = """
        INSERT INTO score_events 
        (id, user_id, source, source_id, points, metadata, occurred_at, created_at)
        VALUES (:id, :userId, :source, :sourceId, :points, :metadata, :occurredAt, :createdAt)
        """, nativeQuery = true)
    void insertScoreEvent(
            @Param("id") UUID id,
            @Param("userId") UUID userId,
            @Param("source") String source,
            @Param("sourceId") UUID sourceId,
            @Param("points") Integer points,
            @Param("metadata") String metadata,
            @Param("occurredAt") LocalDateTime occurredAt,
            @Param("createdAt") LocalDateTime createdAt
    );
}
