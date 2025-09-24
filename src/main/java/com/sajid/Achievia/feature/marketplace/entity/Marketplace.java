package com.sajid.Achievia.feature.marketplace.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "marketplace")
public class Marketplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String shopName;
}