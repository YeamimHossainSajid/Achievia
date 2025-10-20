package com.example.achivia.feature.guildinvitation.repository;

import com.example.achivia.feature.guildinvitation.entity.GuildInvitation;
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
public interface GuildInvitationRepository extends JpaRepository<GuildInvitation, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO guild_invitation (id, guild_id, inviter_user_id, invitee_email, token, expires_at, accepted_at, created_at, updated_at)
        VALUES (:id, :guildId, :inviterUserId, :inviteeEmail, :token, :expiresAt, :acceptedAt, NOW(), NOW())
        ON CONFLICT (id) DO UPDATE
        SET guild_id = :guildId,
            inviter_user_id = :inviterUserId,
            invitee_email = :inviteeEmail,
            token = :token,
            expires_at = :expiresAt,
            accepted_at = :acceptedAt,
            updated_at = NOW()
        """, nativeQuery = true)
    void save(
            @Param("id") UUID id,
            @Param("guildId") UUID guildId,
            @Param("inviterUserId") Long inviterUserId,
            @Param("inviteeEmail") String inviteeEmail,
            @Param("token") String token,
            @Param("expiresAt") String expiresAt,
            @Param("acceptedAt") String acceptedAt
    );

    @Query(value = """
        SELECT * FROM guild_invitation
        WHERE id = :id
        """, nativeQuery = true)
    Optional<GuildInvitation> findById(@Param("id") UUID id);

    @Query(value = """
        SELECT * FROM guild_invitation
        WHERE token = :token
        """, nativeQuery = true)
    GuildInvitation findByToken(@Param("token") String token);

    @Query(value = """
        SELECT * FROM guild_invitation
        WHERE guild_id = :guildId
        ORDER BY created_at DESC
        """, nativeQuery = true)
    List<GuildInvitation> findByGuildId(@Param("guildId") UUID guildId);


    @Query(value = """
        SELECT * FROM guild_invitation
        WHERE invitee_email = :email
        ORDER BY created_at DESC
        """, nativeQuery = true)
    List<GuildInvitation> findByInviteeEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM guild_invitation
        WHERE id = :id
        """, nativeQuery = true)
    void delete(@Param("id") UUID id);
}
