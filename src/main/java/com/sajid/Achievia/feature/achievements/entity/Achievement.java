package com.sajid.Achievia.feature.achievements.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "achievements")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String achievementName;
    private String description;
}