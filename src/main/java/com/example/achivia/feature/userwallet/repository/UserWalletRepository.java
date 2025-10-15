package com.example.achivia.feature.userwallet.repository;

import com.example.achivia.feature.userwallet.entity.UserWallet;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface UserWalletRepository extends JpaRepository<UserWallet, UUID> {

    @Query(value = "SELECT * FROM user_wallets WHERE user_id = :userId", nativeQuery = true)
    UserWallet findByUserIdNative(@Param("userId") Long userId);

    @Modifying
    @Query(value = "UPDATE user_wallets SET balance = :balance, updated_at = :updatedAt WHERE user_id = :userId", nativeQuery = true)
    void updateBalanceNative(@Param("userId") Long userId,
                             @Param("balance") Long balance,
                             @Param("updatedAt") LocalDateTime updatedAt);
}
