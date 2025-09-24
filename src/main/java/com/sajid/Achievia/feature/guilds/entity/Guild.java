package com.sajid.Achievia.feature.guilds.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "guilds")
public class Guild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDateTime createdAt;
}
