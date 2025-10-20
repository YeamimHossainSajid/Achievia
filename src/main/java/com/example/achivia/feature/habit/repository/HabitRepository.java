package com.example.achivia.feature.habit.repository;

import com.example.achivia.feature.habit.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HabitRepository extends JpaRepository<Habit, UUID> {


    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO habit (id, user_id, template_id, name, description, frequency, goal, start_date, is_archived, created_at, updated_at)
        VALUES (:id, :userId, :templateId, :name, :description, :frequency, :goal, :startDate, :isArchived, NOW(), NOW())
        ON CONFLICT (id) DO UPDATE
        SET name = :name,
            description = :description,
            frequency = :frequency,
            goal = :goal,
            is_archived = :isArchived,
            updated_at = NOW()
        """, nativeQuery = true)
    Habit save(
            @Param("id") UUID id,
            @Param("userId") UUID userId,
            @Param("templateId") UUID templateId,
            @Param("name") String name,
            @Param("description") String description,
            @Param("frequency") Integer frequency,
            @Param("goal") Integer goal,
            @Param("startDate") java.sql.Date startDate,
            @Param("isArchived") Boolean isArchived
    );


    @Query(value = """
        SELECT * FROM habit
        WHERE id = :id
        """, nativeQuery = true)
    Optional<Habit> findByIdNative(@Param("id") UUID id);


    @Query(value = """
        SELECT * FROM habit
        """, nativeQuery = true)
    List<Habit> findAll();


    @Query(value = """
        SELECT * FROM habit
        WHERE user_id = :userId
        """, nativeQuery = true)
    List<Habit> findByUserId(@Param("userId") UUID userId);


    @Modifying
    @Transactional
    @Query(value = """
        UPDATE habit
        SET name = :name,
            description = :description,
            frequency = :frequency,
            goal = :goal,
            is_archived = :isArchived,
            updated_at = NOW()
        WHERE id = :id
        """, nativeQuery = true)
    void updateHabit(@Param("id") UUID id,
                     @Param("name") String name,
                     @Param("description") String description,
                     @Param("frequency") String frequency,
                     @Param("goal") Integer goal,
                     @Param("isArchived") Boolean isArchived
    );

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM habit
        WHERE id = :id
        """, nativeQuery = true)
    void deleteByIdNative(@Param("id") UUID id);
}

