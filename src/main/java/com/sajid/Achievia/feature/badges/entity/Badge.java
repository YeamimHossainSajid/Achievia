package com.sajid.Achievia.feature.badges.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "badges")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String badgeName;
}