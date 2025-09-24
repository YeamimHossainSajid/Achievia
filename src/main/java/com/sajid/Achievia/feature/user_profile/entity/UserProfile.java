package com.sajid.Achievia.feature.user_profile.entity;

import com.sajid.Achievia.auth.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String avatarUrl;
    private String bio;
    @Column(columnDefinition = "json")
    private String preferences;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
}