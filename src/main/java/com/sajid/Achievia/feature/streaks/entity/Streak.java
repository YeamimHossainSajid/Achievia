package com.sajid.Achievia.feature.streaks.entity;

import com.sajid.Achievia.feature.habits.entity.Habit;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "streaks")
public class Streak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer currentStreak;
    private Integer longestStreak;
    private LocalDate lastCompleted;
    @ManyToOne @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;
}