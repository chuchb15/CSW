package com.tests4.Game_xxx.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "player_item_t")
public class PlayerItem {
    @EmbeddedId
    private PlayerItemId id;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    private Item item;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

}