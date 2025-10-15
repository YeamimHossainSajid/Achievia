package com.example.achivia.feature.wallettransaction.repository;

import com.example.achivia.feature.wallettransaction.entity.WalletTransaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, UUID> {

    @Query(value = "SELECT * FROM wallet_transactions WHERE user_id = :userId", nativeQuery = true)
    List<WalletTransaction> findByUserIdNative(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM wallet_transactions WHERE id = :id", nativeQuery = true)
    WalletTransaction findByIdNative(@Param("id") UUID id);
}
