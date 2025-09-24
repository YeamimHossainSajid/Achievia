package com.sajid.Achievia.feature.transactions.entity;

import com.sajid.Achievia.auth.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer amount;
    private String currency;
    private LocalDateTime createdAt;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
}