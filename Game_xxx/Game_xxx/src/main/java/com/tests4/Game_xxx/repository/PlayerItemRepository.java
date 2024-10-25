package com.tests4.Game_xxx.repository;

import com.tests4.Game_xxx.entity.Player;
import com.tests4.Game_xxx.entity.PlayerItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerItemRepository extends JpaRepository<PlayerItem, Integer> {
    List<PlayerItem> findByPlayer(Player player);

}