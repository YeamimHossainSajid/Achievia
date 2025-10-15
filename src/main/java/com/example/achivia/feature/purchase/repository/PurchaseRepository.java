package com.example.achivia.feature.purchase.repository;

import com.example.achivia.feature.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

    @Repository
    public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {

        @Query(value = """
        SELECT * FROM purchases
        WHERE user_id = :userId
        ORDER BY created_at DESC
        """, nativeQuery = true)
        List<Purchase> findPurchasesByUserId(@Param("userId") Long userId);


        @Query(value = """
        SELECT * FROM purchases
        WHERE id = :id
        """, nativeQuery = true)
        Optional<Purchase> findPurchaseById(@Param("id") UUID id);

        @Modifying
        @Query(value = """
        INSERT INTO purchases (id, user_id, shop_item_id, price_paid, created_at)
        VALUES (:id, :userId, :shopItemId, :pricePaid, :createdAt)
        """, nativeQuery = true)
        void insertPurchase(
                @Param("id") UUID id,
                @Param("userId") UUID userId,
                @Param("shopItemId") UUID shopItemId,
                @Param("pricePaid") Long pricePaid,
                @Param("createdAt") LocalDateTime createdAt
        );
    }
