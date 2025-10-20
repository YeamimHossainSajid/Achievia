package com.example.achivia.feature.oauth.repository;

import com.example.achivia.feature.oauth.entity.OAuthAccount;
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
public interface OAuthAccountRepository extends JpaRepository<OAuthAccount, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO oauth_account 
        (id, user_id, provider, provider_account_id, access_token, refresh_token, expires_at, created_at) 
        VALUES (:id, :userId, :provider, :providerAccountId, :accessToken, :refreshToken, :expiresAt, NOW())
        ON CONFLICT (id) DO UPDATE
        SET provider = :provider,
            provider_account_id = :providerAccountId,
            access_token = :accessToken,
            refresh_token = :refreshToken,
            expires_at = :expiresAt
        """, nativeQuery = true)
    OAuthAccount save(
            @Param("id") UUID id,
            @Param("userId") UUID userId,
            @Param("provider") String provider,
            @Param("providerAccountId") String providerAccountId,
            @Param("accessToken") String accessToken,
            @Param("refreshToken") String refreshToken,
            @Param("expiresAt") Long expiresAt
    );

    @Query(value = """
        SELECT * FROM oauth_account
        WHERE user_id = :userId
        """, nativeQuery = true)
    List<OAuthAccount> findByUserId(@Param("userId") UUID userId);

    @Query(value = """
        SELECT * FROM oauth_account
        WHERE provider = :provider AND provider_account_id = :providerAccountId
        """, nativeQuery = true)
    Optional<OAuthAccount> findByProviderAndProviderAccountId(
            @Param("provider") String provider,
            @Param("providerAccountId") String providerAccountId
    );

    @Query(value = """
        SELECT * FROM oauth_account
        WHERE id = :id
        """, nativeQuery = true)
    OAuthAccount findByIdNative(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM oauth_account
        WHERE id = :id
        """, nativeQuery = true)
    void deleteByIdNative(@Param("id") UUID id);
}

