package com.tests4.Game_xxx.controller;

import com.tests4.Game_xxx.entity.Item;
import com.tests4.Game_xxx.entity.Player;
import com.tests4.Game_xxx.entity.PlayerItem;
import com.tests4.Game_xxx.entity.dto.PlayerInfoDTO;
import com.tests4.Game_xxx.repository.PlayerRepository;
import com.tests4.Game_xxx.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/item")
    public ResponseEntity<Map<String, String>> addItem(@RequestBody Item item) {
        gameService.addItem(item);
        Map<String, String> response = new HashMap<>();
        response.put("Status", "True");
        response.put("ErrorCode", "200");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/player")
    public ResponseEntity<Map<String, String>> addPlayer(@RequestBody Player player) {
        gameService.addPlayer(player);
        Map<String, String> response = new HashMap<>();
        response.put("Status", "True");
        response.put("ErrorCode", "200");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/playerbuyitem")
    public ResponseEntity<Map<String, String>> buyItem(@RequestBody PlayerItem playerItem) {
        if (playerItem.getId() == null ||
                playerItem.getId().getPlayerId() <= 0 ||
                playerItem.getId().getItemId() == null ||
                playerItem.getId().getItemId().isEmpty()) {

            Map<String, String> response = new HashMap<>();
            response.put("Status", "False");
            response.put("Message", "Invalid PlayerItem data");
            response.put("ErrorCode", "400");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            gameService.addPlayerItem(playerItem);
        } catch (Exception e) {
            log.error("Error adding PlayerItem: {}", e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put("Status", "False");
            response.put("Message", "An error occurred while adding PlayerItem");
            response.put("ErrorCode", "500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        Map<String, String> response = new HashMap<>();
        response.put("Status", "True");
        response.put("ErrorCode", "200");
        return ResponseEntity.ok(response);
    }





    @GetMapping("/playerinfo")
    public ResponseEntity<List<PlayerInfoDTO>> getPlayerInfo() {
        List<PlayerInfoDTO> playerInfo = gameService.getPlayerInfo();
        return ResponseEntity.ok(playerInfo);
    }


}
