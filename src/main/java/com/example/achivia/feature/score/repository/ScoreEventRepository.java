package com.example.achivia.feature.score.repository;

import com.example.achivia.feature.score.entity.ScoreEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScoreEventRepository extends JpaRepository<ScoreEvent, UUID> {


    @Query(value = """
        SELECT * FROM score_event
        WHERE id = :id
        """, nativeQuery = true)
    Optional<ScoreEvent> findByIdNative(UUID id);

    @Query(value = """
        SELECT * FROM score_event
        WHERE user_id = :userId
        """, nativeQuery = true)
    List<ScoreEvent> findByUserId(Long userId);


    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO score_event
        (id, user_id, source, source_id, points, metadata, occurred_at, created_at)
        VALUES (:id, :userId, :source, :sourceId, :points, :metadata, :occurredAt, :createdAt)
        """, nativeQuery = true)
    void insertScoreEvent(UUID id,
                          UUID userId,
                          String source,
                          UUID sourceId,
                          Integer points,
                          String metadata,
                          LocalDateTime occurredAt,
                          LocalDateTime createdAt);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM score_event
        WHERE id = :id
        """, nativeQuery = true)
    void deleteByIdNative(UUID id);

    @Query(value = """
        SELECT * FROM score_event
        """, nativeQuery = true)
    List<ScoreEvent> findAllNative();
}
