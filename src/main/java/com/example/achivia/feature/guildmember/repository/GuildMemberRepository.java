package com.example.achivia.feature.guildmember.repository;

import com.example.achivia.feature.guildmember.entity.GuildMember;
import com.example.achivia.feature.guildmember.entity.GuildMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface GuildMemberRepository extends JpaRepository<GuildMember, GuildMemberId> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO guild_member (guild_id, user_id, role, joined_at)
        VALUES (:guildId, :userId, :role, NOW())
        ON CONFLICT (guild_id, user_id) DO UPDATE
        SET role = :role
        """, nativeQuery = true)
    GuildMember save(
            @Param("guildId") UUID guildId,
            @Param("userId") UUID userId,
            @Param("role") String role
    );

    @Query(value = """
        SELECT * FROM guild_member
        WHERE guild_id = :guildId
        ORDER BY joined_at ASC
        """, nativeQuery = true)
    List<GuildMember> findByGuildId(@Param("guildId") UUID guildId);

    @Query(value = """
        SELECT * FROM guild_member
        WHERE user_id = :userId
        """, nativeQuery = true)
    List<GuildMember> findByUserId(@Param("userId") UUID userId);

    @Query(value = """
        SELECT * FROM guild_member
        WHERE guild_id = :guildId AND user_id = :userId
        """, nativeQuery = true)
    GuildMember findByGuildAndUser(@Param("guildId") UUID guildId, @Param("userId") UUID userId);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM guild_member
        WHERE guild_id = :guildId AND user_id = :userId
        """, nativeQuery = true)
    void delete(@Param("guildId") UUID guildId, @Param("userId") UUID userId);

    @Query(value = """
        SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
        FROM guild_member
        WHERE guild_id = :guildId AND user_id = :userId
        """, nativeQuery = true)
    boolean findbyid(@Param("guildId") UUID guildId, @Param("userId") UUID userId);
}

