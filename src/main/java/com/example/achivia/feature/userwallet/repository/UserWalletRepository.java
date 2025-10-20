package com.example.achivia.feature.userwallet.repository;

import com.example.achivia.feature.userwallet.entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface UserWalletRepository extends JpaRepository<UserWallet, Long> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO user_wallet
        (user_id, balance, created_at, updated_at)
        VALUES (:userId, :balance, :createdAt, :updatedAt)
        """, nativeQuery = true)
    void save(UUID userId, Long balance, LocalDateTime createdAt, LocalDateTime updatedAt);

    @Query(value = """
        SELECT * FROM user_wallet
        WHERE user_id = :userId
        """, nativeQuery = true)
    UserWallet findByUserIdNative(Long userId);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE user_wallet
        SET balance = :balance,
            updated_at = :updatedAt
        WHERE user_id = :userId
        """, nativeQuery = true)
    void updateBalanceNative(Long userId, Long balance, LocalDateTime updatedAt);
}
