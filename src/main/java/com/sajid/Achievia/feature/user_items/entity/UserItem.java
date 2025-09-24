package com.sajid.Achievia.feature.user_items.entity;

import com.sajid.Achievia.auth.model.User;
import com.sajid.Achievia.feature.items.entity.Item;
import jakarta.persistence.*;

@Entity
@Table(name = "user_items")
public class UserItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}