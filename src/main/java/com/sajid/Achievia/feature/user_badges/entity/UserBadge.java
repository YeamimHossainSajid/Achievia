package com.sajid.Achievia.feature.user_badges.entity;

import com.sajid.Achievia.auth.model.User;
import com.sajid.Achievia.feature.badges.entity.Badge;
import jakarta.persistence.*;

@Entity
@Table(name = "user_badges")
public class UserBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne @JoinColumn(name = "badge_id", nullable = false)
    private Badge badge;
}