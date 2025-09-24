package com.sajid.Achievia.feature.challenges.entity;

import com.sajid.Achievia.feature.parties.entity.Party;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "challenges")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String challengeName;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne @JoinColumn(name = "party_id", nullable = false)
    private Party party;
}