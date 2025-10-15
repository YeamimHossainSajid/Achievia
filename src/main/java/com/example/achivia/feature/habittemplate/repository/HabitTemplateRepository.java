package com.example.achivia.feature.habittemplate.repository;

import com.example.achivia.feature.habittemplate.entity.HabitTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface HabitTemplateRepository extends JpaRepository<HabitTemplate, UUID> {

    @Query(value = "SELECT * FROM habit_templates", nativeQuery = true)
    List<HabitTemplate> findAllTemplates();

    @Query(value = "SELECT * FROM habit_templates WHERE id = :id", nativeQuery = true)
    HabitTemplate findByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM habit_templates WHERE created_by_user_id = :userId", nativeQuery = true)
    List<HabitTemplate> findByUserId(@Param("userId") Long userId);

    @Query(value = "UPDATE habit_templates SET name = :name, description = :description, default_frequency = :defaultFrequency, default_goal = :defaultGoal WHERE id = :id RETURNING *", nativeQuery = true)
    HabitTemplate updateTemplate(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("defaultFrequency") String defaultFrequency,
            @Param("defaultGoal") Integer defaultGoal
    );

    @Query(value = "DELETE FROM habit_templates WHERE id = :id", nativeQuery = true)
    void deleteByIdNative(@Param("id") UUID id);
}
