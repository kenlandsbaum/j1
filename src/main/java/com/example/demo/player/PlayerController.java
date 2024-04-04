package com.example.demo.player;

import com.example.demo.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/player")
public class PlayerController {
    @Autowired IPlayerService playerService;
    @GetMapping("")
    ResponseEntity<List<Player>> getPlayers() {
        return new ResponseEntity<List<Player>>(this.playerService.getPlayers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Player> getPlayer(@PathVariable Long id) throws ResponseStatusException {
        Player player =  this.playerService.getPlayer(id);
        if (null != player) {
            return new ResponseEntity<Player>(player, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    ResponseEntity<Player> postPlayer(@RequestBody Player player) {
        try {
            Player createdPlayer = this.playerService.createPlayer(player);
            return new ResponseEntity<Player>(createdPlayer, HttpStatus.CREATED);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
