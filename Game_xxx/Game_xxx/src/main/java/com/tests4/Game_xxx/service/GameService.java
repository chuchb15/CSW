package com.tests4.Game_xxx.service;

import com.tests4.Game_xxx.entity.Item;
import com.tests4.Game_xxx.entity.Player;
import com.tests4.Game_xxx.entity.PlayerItem;
import com.tests4.Game_xxx.entity.PlayerItemId;
import com.tests4.Game_xxx.entity.dto.PlayerInfoDTO;
import com.tests4.Game_xxx.repository.ItemRepository;
import com.tests4.Game_xxx.repository.PlayerItemRepository;
import com.tests4.Game_xxx.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerItemRepository playerItemRepository;

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public void addPlayer(Player player) {
        playerRepository.save(player);
    }

    public void addPlayerItem(PlayerItem playerItem) {
        // Lấy Item từ cơ sở dữ liệu dựa trên itemId
        Item foundItem = itemRepository.findById(playerItem.getId().getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Lấy Player từ cơ sở dữ liệu dựa trên playerId
        Player foundPlayer = playerRepository.findById(playerItem.getId().getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player not found"));

        // Thiết lập item và player cho PlayerItem
        playerItem.setItem(foundItem);
        playerItem.setPlayer(foundPlayer);

        // Lưu PlayerItem vào cơ sở dữ liệu
        playerItemRepository.save(playerItem);
    }

    public List<PlayerInfoDTO> getPlayerInfo() {
        return playerRepository.findAll().stream().map(player -> {
            PlayerInfoDTO dto = new PlayerInfoDTO();
            dto.setPlayerId(player.getPlayerId());
            dto.setPlayerName(player.getPlayerName());
            dto.setPlayerNational(player.getPlayerNational());

            List<PlayerItem> playerItems = playerItemRepository.findByPlayer(player);
            if (!playerItems.isEmpty()) {
                PlayerItem playerItem = playerItems.get(0); // assuming one item for now
                dto.setItemName(playerItem.getItem().getItemName());
                dto.setPrice(playerItem.getItem().getPrice().add(BigDecimal.ONE));
                dto.setItemTypeName(playerItem.getItem().getItemType().getItemTypeName());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public void buyItem(Integer playerId, String itemId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        PlayerItem playerItem = new PlayerItem();
        playerItem.setPlayer(player); // Thiết lập Player
        playerItem.setItem(item); // Thiết lập Item

        playerItemRepository.save(playerItem);
    }

}
