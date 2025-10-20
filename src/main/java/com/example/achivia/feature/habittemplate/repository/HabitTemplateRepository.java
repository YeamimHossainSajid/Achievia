package com.example.achivia.feature.habittemplate.repository;

import com.example.achivia.feature.habittemplate.entity.HabitTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface HabitTemplateRepository extends JpaRepository<HabitTemplate, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO habit_template (id, name, description, default_frequency, default_goal, created_by_user_id, created_at)
        VALUES (:id, :name, :description, :defaultFrequency, :defaultGoal, :createdByUserId, NOW())
        ON CONFLICT (id) DO UPDATE
        SET name = :name,
            description = :description,
            default_frequency = :defaultFrequency,
            default_goal = :defaultGoal
        """, nativeQuery = true)
    HabitTemplate save(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("defaultFrequency") Integer defaultFrequency,
            @Param("defaultGoal") Integer defaultGoal,
            @Param("createdByUserId") Long createdByUserId
    );

    @Query(value = """
        SELECT * FROM habit_template
        WHERE id = :id
        """, nativeQuery = true)
    HabitTemplate findByIdNative(@Param("id") UUID id);

    @Query(value = """
        SELECT * FROM habit_template
        """, nativeQuery = true)
    List<HabitTemplate> findAllTemplates();

    @Query(value = """
        SELECT * FROM habit_template
        WHERE created_by_user_id = :userId
        """, nativeQuery = true)
    List<HabitTemplate> findByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE habit_template
        SET name = :name,
            description = :description,
            default_frequency = :defaultFrequency,
            default_goal = :defaultGoal
        WHERE id = :id
        """, nativeQuery = true)
    HabitTemplate updateTemplate(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("defaultFrequency") String defaultFrequency,
            @Param("defaultGoal") Integer defaultGoal
    );

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM habit_template
        WHERE id = :id
        """, nativeQuery = true)
    void deleteByIdNative(@Param("id") UUID id);
}
