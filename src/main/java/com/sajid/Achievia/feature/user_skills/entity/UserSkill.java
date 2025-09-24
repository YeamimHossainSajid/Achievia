package com.sajid.Achievia.feature.user_skills.entity;

import com.sajid.Achievia.auth.model.User;
import com.sajid.Achievia.feature.skills.entity.Skill;
import jakarta.persistence.*;

@Entity
@Table(name = "user_skills")
public class UserSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;
}