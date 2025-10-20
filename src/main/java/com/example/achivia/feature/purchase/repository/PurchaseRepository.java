package com.example.achivia.feature.purchase.repository;

import com.example.achivia.feature.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {

    @Query(value = """
        SELECT * FROM purchase
        WHERE user_id = :userId
        """, nativeQuery = true)
    List<Purchase> findPurchasesByUserId(Long userId);

    @Query(value = """
        SELECT * FROM purchase
        WHERE id = :id
        """, nativeQuery = true)
    Optional<Purchase> findByIdNative(UUID id);

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO purchase (id, user_id, shop_item_id, price_paid, created_at)
        VALUES (:id, :userId, :shopItemId, :pricePaid, NOW())
        """, nativeQuery = true)
    void save(UUID id, Long userId, UUID shopItemId, Double pricePaid);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM purchase
        WHERE id = :id
        """, nativeQuery = true)
    void deleteByIdNative(UUID id);
}
