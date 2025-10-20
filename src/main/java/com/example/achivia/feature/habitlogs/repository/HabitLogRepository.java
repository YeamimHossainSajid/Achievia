package com.example.achivia.feature.habitlogs.repository;

import com.example.achivia.feature.habitlogs.entity.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HabitLogRepository extends JpaRepository<HabitLog, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO habit_log (id, habit_id, user_id, occurred_on, count, notes, created_at)
        VALUES (:id, :habitId, :userId, :occurredOn, :count, :notes, NOW())
        ON CONFLICT (id) DO UPDATE
        SET count = :count,
            notes = :notes
        """, nativeQuery = true)
    HabitLog save(
            @Param("id") UUID id,
            @Param("habitId") UUID habitId,
            @Param("userId") UUID userId,
            @Param("occurredOn") LocalDate occurredOn,
            @Param("count") Integer count,
            @Param("notes") String notes
    );

    @Query(value = """
        SELECT * FROM habit_log
        WHERE id = :id
        """, nativeQuery = true)
    Optional<HabitLog> findByIdNative(@Param("id") UUID id);

    @Query(value = """
        SELECT * FROM habit_log
        WHERE user_id = :userId
        """, nativeQuery = true)
    List<HabitLog> findByUserId(@Param("userId") UUID userId);

    @Query(value = """
        SELECT * FROM habit_log
        WHERE habit_id = :habitId
        """, nativeQuery = true)
    List<HabitLog> findByHabitId(@Param("habitId") UUID habitId);

    @Query(value = """
        SELECT * FROM habit_log
        WHERE occurred_on = :date
        """, nativeQuery = true)
    List<HabitLog> findByDate(@Param("date") LocalDate date);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE habit_log
        SET count = :count,
            notes = :notes
        WHERE id = :id
        """, nativeQuery = true)
    void updateHabitLog(@Param("id") UUID id,
                        @Param("count") Integer count,
                        @Param("notes") String notes);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM habit_log
        WHERE id = :id
        """, nativeQuery = true)
    void deleteByIdNative(@Param("id") UUID id);
}
