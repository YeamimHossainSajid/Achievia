package com.sajid.Achievia.feature.monsters.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "monsters")
public class Monster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer level;
    private Integer health;
}