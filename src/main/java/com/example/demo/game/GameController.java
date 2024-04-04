package com.example.demo.game;

import com.example.demo.dto.DictionaryApiDTO;
import com.example.demo.dto.Guess;
import com.example.demo.dto.GuessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/game")
public class GameController {
    @Autowired IGameService gameService;
    @PostMapping("")
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game createdGame = this.gameService.createGame(game);
        return new ResponseEntity<Game>(createdGame, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) throws ResponseStatusException {
        Game game = this.gameService.getGame(id);
        if (null != game) {
           return new ResponseEntity<Game>(game, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
