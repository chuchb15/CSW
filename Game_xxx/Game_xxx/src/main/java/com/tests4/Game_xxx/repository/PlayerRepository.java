package com.tests4.Game_xxx.repository;

import com.tests4.Game_xxx.entity.Player;
import com.tests4.Game_xxx.entity.dto.PlayerInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Query("SELECT new com.tests4.Game_xxx.entity.dto.PlayerInfoDTO(p.playerId, p.playerName, p.playerNational, i.itemName, i.price, it.itemTypeName) " +
            "FROM Player p " +
            "JOIN PlayerItem pi ON p.playerId = pi.player.playerId " +
            "JOIN Item i ON pi.item.itemId = i.itemId " +
            "JOIN ItemType it ON i.itemType.itemTypeId = it.itemTypeId")
    List<PlayerInfoDTO> getPlayerInfo();
}