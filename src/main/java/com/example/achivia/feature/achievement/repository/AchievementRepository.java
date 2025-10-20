package com.example.achivia.feature.achievement.repository;

import com.example.achivia.feature.achievement.entity.Achievement;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, UUID> {

    @Query(value = "SELECT * FROM achievement", nativeQuery = true)
    List<Achievement> findAll();

    @Query(value = "SELECT * FROM achievement WHERE id = :id", nativeQuery = true)
    Optional<Achievement> findById(@Param("id") UUID id);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM achievement WHERE slug = :slug", nativeQuery = true)
    boolean existsBySlug(@Param("slug") String slug);

    @Modifying
    @Query(value = """
        INSERT INTO achievement (id, name, description, icon_url, slug, points_reward, created_at)
        VALUES (:id, :name, :description, :iconUrl, :slug, :pointsReward, NOW())
        """, nativeQuery = true)
    void save(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("iconUrl") String iconUrl,
            @Param("slug") String slug,
            @Param("pointsReward") Integer pointsReward
    );

    @Modifying
    @Query(value = """
        UPDATE achievement
        SET name = :name,
            description = :description,
            icon_url = :iconUrl,
            slug = :slug,
            points_reward = :pointsReward
        WHERE id = :id
        """, nativeQuery = true)
    void update(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("iconUrl") String iconUrl,
            @Param("slug") String slug,
            @Param("pointsReward") Integer pointsReward
    );

    @Modifying
    @Query(value = "DELETE FROM achievement WHERE id = :id", nativeQuery = true)
    void deleteById(@Param("id") UUID id);
}

