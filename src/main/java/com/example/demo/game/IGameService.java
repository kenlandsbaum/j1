package com.example.demo.game;

import com.example.demo.dto.DictionaryApiDTO;
import com.example.demo.dto.Guess;
import com.example.demo.dto.GuessResult;
import org.springframework.stereotype.Service;

public interface IGameService {
    public Game createGame(Game newGame);
    public Game getGame(Long id);
    public DictionaryApiDTO startGame(Long id);
    public GuessResult checkGuess(Guess guess);

}
