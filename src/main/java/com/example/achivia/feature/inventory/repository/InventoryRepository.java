package com.example.achivia.feature.inventory.repository;

import com.example.achivia.feature.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO inventory (id, user_id, shop_item_id, quantity, acquired_at)
        VALUES (:id, :userId, :shopItemId, :quantity, NOW())
        ON CONFLICT (id) DO UPDATE
        SET quantity = :quantity
        """, nativeQuery = true)
    Inventory save(
            @Param("id") UUID id,
            @Param("userId") Long userId,
            @Param("shopItemId") UUID shopItemId,
            @Param("quantity") Integer quantity
    );

    @Query(value = """
        SELECT * FROM inventory
        WHERE id = :id
        """, nativeQuery = true)
    Inventory findByIdNative(@Param("id") UUID id);

    @Query(value = """
        SELECT * FROM inventory
        """, nativeQuery = true)
    List<Inventory> findAllNative();

    @Query(value = """
        SELECT * FROM inventory
        WHERE user_id = :userId
        """, nativeQuery = true)
    List<Inventory> findByUserId(@Param("userId") Long userId);

    @Query(value = """
        SELECT * FROM inventory
        WHERE shop_item_id = :shopItemId
        """, nativeQuery = true)
    List<Inventory> findByShopItemId(@Param("shopItemId") UUID shopItemId);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE inventory
        SET quantity = :quantity
        WHERE id = :id
        """, nativeQuery = true)
    Inventory updateInventory(
            @Param("id") UUID id,
            @Param("quantity") Integer quantity
    );


    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM inventory
        WHERE id = :id
        """, nativeQuery = true)
    void deleteByIdNative(@Param("id") UUID id);
}
