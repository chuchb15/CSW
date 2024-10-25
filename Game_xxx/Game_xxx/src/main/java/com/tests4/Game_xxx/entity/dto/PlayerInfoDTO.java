package com.tests4.Game_xxx.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlayerInfoDTO {
    private int playerId;
    private String playerName;
    private String playerNational;
    private String itemName;
    private BigDecimal  price;
    private String itemTypeName;

    public PlayerInfoDTO(int playerId, String playerName, String playerNational, String itemName, BigDecimal price, String itemTypeName) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerNational = playerNational;
        this.itemName = itemName;
        this.price = price;
        this.itemTypeName = itemTypeName;
    }

    public PlayerInfoDTO() {

    }


}
