package com.sajid.Achievia.feature.user_settings.entity;

import com.sajid.Achievia.auth.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user_settings")
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean notificationsEnabled;
    private String privacyLevel;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
}