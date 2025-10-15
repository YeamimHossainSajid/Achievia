package com.example.achivia.feature.habit.repository;

import com.example.achivia.feature.habit.entity.Habit;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface HabitRepository extends JpaRepository<Habit, UUID> {

    @Query(value = "SELECT * FROM habits WHERE id = :id", nativeQuery = true)
    Optional<Habit> findByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM habits WHERE user_id = :userId", nativeQuery = true)
    List<Habit> findByUserId(@Param("userId") UUID userId);

    @Query(value = "SELECT * FROM habits WHERE is_archived = false", nativeQuery = true)
    List<Habit> findAllActive();

    @Modifying
    @Query(value = "DELETE FROM habits WHERE id = :id", nativeQuery = true)
    void deleteByIdNative(@Param("id") UUID id);

    @Modifying
    @Query(value = "UPDATE habits SET name = :name, description = :description, frequency = :frequency, goal = :goal, is_archived = :isArchived, updated_at = NOW() WHERE id = :id", nativeQuery = true)
    void updateHabit(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("frequency") String frequency,
            @Param("goal") Integer goal,
            @Param("isArchived") Boolean isArchived
    );
}
