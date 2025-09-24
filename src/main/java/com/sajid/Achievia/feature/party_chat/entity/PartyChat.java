package com.sajid.Achievia.feature.party_chat.entity;

import com.sajid.Achievia.auth.model.User;
import com.sajid.Achievia.feature.parties.entity.Party;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "party_chat")
public class PartyChat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private LocalDateTime sentAt;
    @ManyToOne @JoinColumn(name = "party_id", nullable = false)
    private Party party;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}