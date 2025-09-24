package com.sajid.Achievia.feature.tasks_logs.entity;

import com.sajid.Achievia.feature.tasks.entity.Task;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "task_logs")
public class TaskLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime completedAt;
    @ManyToOne @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}