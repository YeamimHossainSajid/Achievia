package com.example.achivia.feature.habitlogs.repository;

import com.example.achivia.feature.habitlogs.entity.HabitLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface HabitLogRepository extends JpaRepository<HabitLog, UUID> {

    @Query(value = "SELECT * FROM habit_logs WHERE id = :id", nativeQuery = true)
    Optional<HabitLog> findByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM habit_logs WHERE user_id = :userId", nativeQuery = true)
    List<HabitLog> findByUserId(@Param("userId") UUID userId);

    @Query(value = "SELECT * FROM habit_logs WHERE habit_id = :habitId", nativeQuery = true)
    List<HabitLog> findByHabitId(@Param("habitId") UUID habitId);

    @Query(value = "SELECT * FROM habit_logs WHERE user_id = :userId AND habit_id = :habitId", nativeQuery = true)
    List<HabitLog> findByUserAndHabit(@Param("userId") UUID userId, @Param("habitId") UUID habitId);

    @Query(value = "SELECT * FROM habit_logs WHERE occurred_on = :date", nativeQuery = true)
    List<HabitLog> findByDate(@Param("date") LocalDate date);

    @Modifying
    @Query(value = "DELETE FROM habit_logs WHERE id = :id", nativeQuery = true)
    void deleteByIdNative(@Param("id") UUID id);

    @Modifying
    @Query(value = "UPDATE habit_logs SET count = :count, notes = :notes WHERE id = :id", nativeQuery = true)
    void updateHabitLog(@Param("id") UUID id,
                        @Param("count") Integer count,
                        @Param("notes") String notes);
}
