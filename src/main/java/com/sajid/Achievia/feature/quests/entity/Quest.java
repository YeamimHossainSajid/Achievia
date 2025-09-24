package com.sajid.Achievia.feature.quests.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quests")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questName;
    private String description;
}