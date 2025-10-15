package com.example.achivia.feature.oauth.repository;

import com.example.achivia.feature.oauth.entity.OAuthAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OAuthAccountRepository extends JpaRepository<OAuthAccount, UUID> {

    @Query(value = "SELECT * FROM oauth_accounts WHERE user_id = :userId", nativeQuery = true)
    List<OAuthAccount> findByUserId(UUID userId);

    @Query(value = "SELECT * FROM oauth_accounts WHERE provider = :provider AND provider_account_id = :providerAccountId", nativeQuery = true)
    Optional<OAuthAccount> findByProviderAndProviderAccountId(String provider, String providerAccountId);
}
