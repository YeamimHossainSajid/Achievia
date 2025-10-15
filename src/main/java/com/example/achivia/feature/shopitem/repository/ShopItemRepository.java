package com.example.achivia.feature.shopitem.repository;

import com.example.achivia.feature.shopitem.entity.ShopItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShopItemRepository extends JpaRepository<ShopItem, UUID> {

    @Query(value = "SELECT * FROM shop_items WHERE id = :id", nativeQuery = true)
    Optional<ShopItem> findByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM shop_items ORDER BY created_at DESC", nativeQuery = true)
    List<ShopItem> findAllNative();

    @Modifying
    @Query(value = """
        INSERT INTO shop_items
        (id, slug, name, description, icon_url, price, is_active, created_at, updated_at)
        VALUES (:id, :slug, :name, :description, :iconUrl, :price, :isActive, :createdAt, :updatedAt)
        """, nativeQuery = true)
    void insertShopItem(
            @Param("id") UUID id,
            @Param("slug") String slug,
            @Param("name") String name,
            @Param("description") String description,
            @Param("iconUrl") String iconUrl,
            @Param("price") Long price,
            @Param("isActive") Boolean isActive,
            @Param("createdAt") java.time.LocalDateTime createdAt,
            @Param("updatedAt") java.time.LocalDateTime updatedAt
    );
}
