package com.example.achivia.feature.competition.repository;


import com.example.achivia.feature.competition.entity.Competition;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    @Query(value = "SELECT * FROM competition", nativeQuery = true)
    List<Competition> findAllCompetitions();

    @Query(value = "SELECT * FROM competition WHERE id = :id", nativeQuery = true)
    Optional<Competition> findCompetitionById(@Param("id") UUID id);

    @Query(value = "SELECT * FROM competition WHERE slug = :slug", nativeQuery = true)
    Optional<Competition> findBySlug(@Param("slug") String slug);

    @Query(value = "SELECT * FROM competition WHERE host_user_id = :hostUserId", nativeQuery = true)
    List<Competition> findByHostUserId(@Param("hostUserId") UUID hostUserId);

    @Query(value = "SELECT * FROM competition WHERE visibility = :visibility", nativeQuery = true)
    List<Competition> findByVisibility(@Param("visibility") String visibility);

    @Query(value = """
        SELECT * FROM competition 
        WHERE start_at <= :now AND end_at >= :now
        """, nativeQuery = true)
    List<Competition> findActiveCompetitions(@Param("now") LocalDateTime now);

    @Query(value = """
        SELECT * FROM competition 
        WHERE end_at < :now
        """, nativeQuery = true)
    List<Competition> findEndedCompetitions(@Param("now") LocalDateTime now);

    @Query(value = """
        SELECT * FROM competition 
        WHERE start_at > :now
        """, nativeQuery = true)
    List<Competition> findUpcomingCompetitions(@Param("now") LocalDateTime now);

    @Modifying
    @Query(value = """
        INSERT INTO competition
        (id, slug, name, description, start_at, end_at, visibility, host_user_id, created_at, updated_at)
        VALUES (:id, :slug, :name, :description, :startAt, :endAt, :visibility, :hostUserId, NOW(), NOW())
        """, nativeQuery = true)
    void insertCompetition(
            @Param("id") UUID id,
            @Param("slug") String slug,
            @Param("name") String name,
            @Param("description") String description,
            @Param("startAt") LocalDateTime startAt,
            @Param("endAt") LocalDateTime endAt,
            @Param("visibility") String visibility,
            @Param("hostUserId") UUID hostUserId
    );


    @Modifying
    @Query(value = """
        UPDATE competition
        SET slug = :slug,
            name = :name,
            description = :description,
            start_at = :startAt,
            end_at = :endAt,
            visibility = :visibility,
            host_user_id = :hostUserId,
            updated_at = NOW()
        WHERE id = :id
        """, nativeQuery = true)
    void updateCompetition(
            @Param("id") UUID id,
            @Param("slug") String slug,
            @Param("name") String name,
            @Param("description") String description,
            @Param("startAt") LocalDateTime startAt,
            @Param("endAt") LocalDateTime endAt,
            @Param("visibility") String visibility,
            @Param("hostUserId") UUID hostUserId
    );


    @Modifying
    @Query(value = "DELETE FROM competition WHERE id = :id", nativeQuery = true)
    void deleteCompetitionById(@Param("id") UUID id);

    @Modifying
    @Query(value = "DELETE FROM competition WHERE slug = :slug", nativeQuery = true)
    void deleteCompetitionBySlug(@Param("slug") String slug);

    @Query(value = "SELECT COUNT(*) FROM competition", nativeQuery = true)
    long countAllCompetitions();

    @Query(value = "SELECT COUNT(*) FROM competition" +
            " WHERE visibility = :visibility", nativeQuery = true)
    long countByVisibility(@Param("visibility") String visibility);

    @Query(value = """
        SELECT COUNT(*) FROM competition
        WHERE host_user_id = :hostUserId
        """, nativeQuery = true)
    long countByHostUser(@Param("hostUserId") UUID hostUserId);
}

