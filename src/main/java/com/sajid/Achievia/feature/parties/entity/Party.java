package com.sajid.Achievia.feature.parties.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parties")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDateTime createdAt;
}