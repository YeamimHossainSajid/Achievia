package com.sajid.Achievia.feature.quest_logs.entity;

import com.sajid.Achievia.auth.model.User;
import com.sajid.Achievia.feature.quests.entity.Quest;
import jakarta.persistence.*;

@Entity
@Table(name = "quest_logs")
public class QuestLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer progress;
    @ManyToOne @JoinColumn(name = "quest_id", nullable = false)
    private Quest quest;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}