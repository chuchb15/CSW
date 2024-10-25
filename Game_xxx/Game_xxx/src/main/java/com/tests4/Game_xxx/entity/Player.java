package com.tests4.Game_xxx.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "player_t")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int playerId;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "player_national")
    private String playerNational;
}