package com.sajid.Achievia.feature.rewards.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rewards")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rewardName;
    private Integer cost;
    private String type;
}