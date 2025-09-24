package com.sajid.Achievia.feature.challenge_participants.entity;

import com.sajid.Achievia.auth.model.User;
import com.sajid.Achievia.feature.challenges.entity.Challenge;
import jakarta.persistence.*;

@Entity
@Table(name = "challenge_participants")
public class ChallengeParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer progress;
    @ManyToOne @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
}