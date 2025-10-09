package com.example.achivia.feature.achievement.repository;

import com.example.achivia.feature.achievement.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, UUID> {

    @Query(
            value = """
            SELECT *
            FROM achievements
            ORDER BY created_at DESC
            """,
            nativeQuery = true
    )
    List<Achievement> fetchAllAchievements();

    @Query(
            value = """
            SELECT *
            FROM achievements
            WHERE id = :id
            """,
            nativeQuery = true
    )
    Optional<Achievement> fetchAchievementById(@Param("id") UUID id);

    @Query(
            value = """
            SELECT *
            FROM achievements
            WHERE slug = :slug
            """,
            nativeQuery = true
    )
    Optional<Achievement> fetchAchievementBySlug(@Param("slug") String slug);

    @Query(
            value = """
            SELECT CASE
                       WHEN COUNT(*) > 0 THEN true
                       ELSE false
                   END
            FROM achievements
            WHERE slug = :slug
            """,
            nativeQuery = true
    )
    boolean checkSlugExists(@Param("slug") String slug);

    @Query(
            value = """
            INSERT INTO achievements
                (id, slug, name, description, icon_url, points_reward, created_at)
            VALUES
                (:id, :slug, :name, :description, :iconUrl, :pointsReward, :createdAt)
            RETURNING *
            """,
            nativeQuery = true
    )
    Achievement insertNewAchievement(
            @Param("id") UUID id,
            @Param("slug") String slug,
            @Param("name") String name,
            @Param("description") String description,
            @Param("iconUrl") String iconUrl,
            @Param("pointsReward") Integer pointsReward,
            @Param("createdAt") java.time.LocalDateTime createdAt
    );

    @Query(
            value = """
            UPDATE achievements
            SET slug = :slug,
                name = :name,
                description = :description,
                icon_url = :iconUrl,
                points_reward = :pointsReward
            WHERE id = :id
            RETURNING *
            """,
            nativeQuery = true
    )
    Achievement updateExistingAchievement(
            @Param("id") UUID id,
            @Param("slug") String slug,
            @Param("name") String name,
            @Param("description") String description,
            @Param("iconUrl") String iconUrl,
            @Param("pointsReward") Integer pointsReward
    );

    @Query(
            value = """
            DELETE FROM achievements
            WHERE id = :id
            """,
            nativeQuery = true
    )
    void deleteAchievementById(@Param("id") UUID id);


    @Query(
            value = """
            SELECT CASE
                       WHEN COUNT(*) > 0 THEN true
                       ELSE false
                   END
            FROM achievements
            WHERE slug = :slug
            """,
            nativeQuery = true
    )
    boolean existsBySlug(@Param("slug") String slug);

}
