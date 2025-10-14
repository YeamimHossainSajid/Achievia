package com.example.achivia.feature.guild.repository;

import com.example.achivia.feature.guild.entity.Guild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GuildRepository extends JpaRepository<Guild, UUID> {

    @Query(value = "SELECT * FROM guilds WHERE id = :id", nativeQuery = true)
    Guild findByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM guilds WHERE slug = :slug", nativeQuery = true)
    Guild findBySlug(@Param("slug") String slug);

    @Query(value = "SELECT * FROM guilds WHERE owner_user_id = :ownerUserId", nativeQuery = true)
    List<Guild> findByOwnerUserId(@Param("ownerUserId") Long ownerUserId);

    @Query(value = "SELECT * FROM guilds", nativeQuery = true)
    List<Guild> findAllNative();

    @Query(value = "DELETE FROM guilds WHERE id = :id RETURNING *", nativeQuery = true)
    Guild deleteByIdNative(@Param("id") UUID id);
}
