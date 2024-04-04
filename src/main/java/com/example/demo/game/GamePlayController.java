package com.example.demo.game;

import com.example.demo.dto.DictionaryApiDTO;
import com.example.demo.dto.Guess;
import com.example.demo.dto.GuessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/play")
public class GamePlayController {

    private @Autowired IGameService gameService;

    @PostMapping("")
    public ResponseEntity<DictionaryApiDTO> startGame(@RequestBody Game game) {
        DictionaryApiDTO result = this.gameService.startGame(game.getId());
        return new ResponseEntity<DictionaryApiDTO>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuessResult> checkGuess(@RequestBody Guess guess) {
        GuessResult result = this.gameService.checkGuess(guess);
        return new ResponseEntity<GuessResult>(result, HttpStatus.ACCEPTED);
    }
}
