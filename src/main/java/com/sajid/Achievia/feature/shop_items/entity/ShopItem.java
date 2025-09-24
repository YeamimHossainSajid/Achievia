package com.sajid.Achievia.feature.shop_items.entity;

import com.sajid.Achievia.feature.items.entity.Item;
import com.sajid.Achievia.feature.marketplace.entity.Marketplace;
import jakarta.persistence.*;

@Entity
@Table(name = "shop_items")
public class ShopItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "marketplace_id", nullable = false)
    private Marketplace marketplace;
    @ManyToOne @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}