
package com.sajid.Achievia.feature.tasks.entity;

import com.sajid.Achievia.auth.model.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String taskTitle;
    private String status;
    private LocalDate dueDate;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
}