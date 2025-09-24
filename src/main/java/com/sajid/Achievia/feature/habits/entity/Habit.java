package com.sajid.Achievia.feature.habits.entity;

import com.sajid.Achievia.auth.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "habits")
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String habitName;
    private String type;
    private String difficulty;
    private LocalDateTime createdAt;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
}