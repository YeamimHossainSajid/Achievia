package com.example.achivia.feature.guildinvitation.repository;

import com.example.achivia.feature.guildinvitation.entity.GuildInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GuildInvitationRepository extends JpaRepository<GuildInvitation, UUID> {

    @Query(value = "SELECT * FROM guild_invitations WHERE id = :id", nativeQuery = true)
    GuildInvitation findByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM guild_invitations WHERE token = :token", nativeQuery = true)
    GuildInvitation findByToken(@Param("token") String token);

    @Query(value = "SELECT * FROM guild_invitations WHERE guild_id = :guildId", nativeQuery = true)
    List<GuildInvitation> findByGuildId(@Param("guildId") UUID guildId);

    @Query(value = "SELECT * FROM guild_invitations WHERE invitee_email = :email", nativeQuery = true)
    List<GuildInvitation> findByInviteeEmail(@Param("email") String email);

    @Query(value = "DELETE FROM guild_invitations WHERE id = :id RETURNING *", nativeQuery = true)
    GuildInvitation deleteByIdNative(@Param("id") UUID id);
}
