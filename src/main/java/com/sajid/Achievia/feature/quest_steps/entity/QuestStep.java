package com.sajid.Achievia.feature.quest_steps.entity;

import com.sajid.Achievia.feature.quests.entity.Quest;
import jakarta.persistence.*;

@Entity
@Table(name = "quest_steps")
public class QuestStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String stepName;
    private Boolean isRequired;
    @ManyToOne
    @JoinColumn(name = "quest_id", nullable = false)
    private Quest quest;
}