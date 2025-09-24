package com.sajid.Achievia.feature.party_members.entity;

import com.sajid.Achievia.auth.model.User;
import com.sajid.Achievia.feature.parties.entity.Party;
import jakarta.persistence.*;

@Entity
@Table(name = "party_members")
public class PartyMember {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;
    @ManyToOne @JoinColumn(name = "party_id", nullable = false)
    private Party party;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
}