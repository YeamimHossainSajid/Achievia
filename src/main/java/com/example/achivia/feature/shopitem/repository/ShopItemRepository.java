package com.example.achivia.feature.shopitem.repository;

import com.example.achivia.feature.shopitem.entity.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShopItemRepository extends JpaRepository<ShopItem, UUID> {

    @Query(value = """
        SELECT * FROM shop_item
        WHERE id = :id
        """, nativeQuery = true)
    Optional<ShopItem> findByIdNative(UUID id);

    @Query(value = """
        SELECT * FROM shop_item
        """, nativeQuery = true)
    List<ShopItem> findAllNative();

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO shop_item
        (id, slug, name, description, icon_url, price, is_active, created_at, updated_at)
        VALUES (:id, :slug, :name, :description, :iconUrl, :price, :isActive, :createdAt, :updatedAt)
        """, nativeQuery = true)
    void insertShopItem(UUID id,
                        String slug,
                        String name,
                        String description,
                        String iconUrl,
                        Long price,
                        Boolean isActive,
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM shop_item
        WHERE id = :id
        """, nativeQuery = true)
    void deleteByIdNative(UUID id);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE shop_item
        SET slug = :slug,
            name = :name,
            description = :description,
            icon_url = :iconUrl,
            price = :price,
            is_active = :isActive,
            updated_at = :updatedAt
        WHERE id = :id
        """, nativeQuery = true)
    void updateShopItem(UUID id,
                        String slug,
                        String name,
                        String description,
                        String iconUrl,
                        Double price,
                        Boolean isActive,
                        LocalDateTime updatedAt);
}
