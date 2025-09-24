package com.sajid.Achievia.feature.user_rewards.entity;

import com.sajid.Achievia.auth.model.User;
import com.sajid.Achievia.feature.rewards.entity.Reward;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_rewards")
public class UserReward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime purchasedAt;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne @JoinColumn(name = "reward_id", nullable = false)
    private Reward reward;
}