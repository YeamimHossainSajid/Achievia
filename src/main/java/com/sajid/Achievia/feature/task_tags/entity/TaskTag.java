package com.sajid.Achievia.feature.task_tags.entity;

import com.sajid.Achievia.feature.tags.entity.Tag;
import com.sajid.Achievia.feature.tasks.entity.Task;
import jakarta.persistence.*;

@Entity
@Table(name = "task_tags")
public class TaskTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "task_id", nullable = false)
    private Task task;
    @ManyToOne @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;
}