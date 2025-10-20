package com.example.achivia.feature.wallettransaction.repository;

import com.example.achivia.feature.wallettransaction.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO wallet_transaction (
            id,
            user_id,
            amount,
            reason,
            reference_id,
            metadata,
            created_at
        )
        VALUES (
            :id,
            :userId,
            :amount,
            :reason,
            :referenceId,
            :metadata,
            NOW()
        )
        """, nativeQuery = true)
    void save(
            UUID id,
            Long userId,
            Long amount,
            String reason,
            String referenceId,
            String metadata
    );


    @Query(value = """
        SELECT *
        FROM wallet_transaction
        WHERE user_id = :userId
        ORDER BY created_at DESC
        """, nativeQuery = true)
    List<WalletTransaction> findByUserId(Long userId);

    @Query(value = """
        SELECT *
        FROM wallet_transaction
        WHERE id = :id
        """, nativeQuery = true)
    Optional<WalletTransaction> findById(UUID id);
}
