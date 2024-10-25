package com.tests4.Game_xxx.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class PlayerItemId implements Serializable {
    private String itemId;
    private int playerId;
}
