package com.example.achivia.feature.guildmember.repository;

import com.example.achivia.feature.guildmember.entity.GuildMember;
import com.example.achivia.feature.guildmember.entity.GuildMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GuildMemberRepository extends JpaRepository<GuildMember, GuildMemberId> {

    @Query(value = "SELECT * FROM guild_members WHERE guild_id = :guildId", nativeQuery = true)
    List<GuildMember> findByGuildId(@Param("guildId") UUID guildId);

    @Query(value = "SELECT * FROM guild_members WHERE user_id = :userId", nativeQuery = true)
    List<GuildMember> findByUserId(@Param("userId") UUID userId);

    @Query(value = "SELECT * FROM guild_members WHERE guild_id = :guildId AND user_id = :userId", nativeQuery = true)
    GuildMember findByGuildAndUser(@Param("guildId") UUID guildId, @Param("userId") UUID userId);

    @Query(value = "DELETE FROM guild_members WHERE guild_id = :guildId AND user_id = :userId RETURNING *", nativeQuery = true)
    GuildMember deleteByGuildAndUser(@Param("guildId") UUID guildId, @Param("userId") UUID userId);
}
