package com.example.achivia.feature.guild.repository;

import com.example.achivia.feature.guild.entity.Guild;
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
public interface GuildRepository extends JpaRepository<Guild, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO guild (id, name, slug, description, owner_user_id, icon_url, created_at, updated_at)
        VALUES (:id, :name, :slug, :description, :ownerUserId, :iconUrl, NOW(), NOW())
        ON CONFLICT (id) DO UPDATE 
        SET name = :name,
            slug = :slug,
            description = :description,
            icon_url = :iconUrl,
            updated_at = NOW()
        """, nativeQuery = true)
    void save(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("slug") String slug,
            @Param("description") String description,
            @Param("ownerUserId") Long ownerUserId,
            @Param("iconUrl") String iconUrl
    );

    @Query(value = """
        SELECT * FROM guild
        WHERE id = :id
        """, nativeQuery = true)
    Optional<Guild> findById(@Param("id") UUID id);

    @Query(value = """
        SELECT * FROM guild
        WHERE slug = :slug
        """, nativeQuery = true)
    Guild findBySlug(@Param("slug") String slug);

    @Query(value = """
        SELECT * FROM guild
        WHERE owner_user_id = :ownerUserId
        ORDER BY created_at DESC
        """, nativeQuery = true)
    List<Guild> findByOwnerUserId(@Param("ownerUserId") Long ownerUserId);

    @Query(value = """
        SELECT * FROM guild
        ORDER BY created_at DESC
        """, nativeQuery = true)
    List<Guild> findAllNative();

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM guild
        WHERE id = :id
        """, nativeQuery = true)
    void delete(@Param("id") UUID id);
}

