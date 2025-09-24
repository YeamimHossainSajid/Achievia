package com.sajid.Achievia.feature.tasks_type.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "task_types")
public class TaskType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String typeName;
}