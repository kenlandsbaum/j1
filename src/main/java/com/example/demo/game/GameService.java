package com.example.demo.game;

import com.example.demo.dictionary.IDictionaryService;
import com.example.demo.dto.DictionaryApiDTO;
import com.example.demo.dto.Guess;
import com.example.demo.dto.GuessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class GameService implements  IGameService {
    @Autowired  GameRepository gameRepository;
    @Autowired IDictionaryService dictionaryService;
    @Value("${dictionary.size}")
    private int dictionarySize;
    private String currentWord;
    @Override
    public Game createGame(Game newGame) {
        return this.gameRepository.save(newGame);
    }

    public Game getGame(Long id) {
        Optional<Game> game = this.gameRepository.findById(id);
        return game.orElse(null);
    }

    public DictionaryApiDTO startGame(Long id) {
        Random random = new Random();
        int randomNumber = random.nextInt(dictionarySize);
        DictionaryApiDTO result = this.dictionaryService.getDictionaryWord(randomNumber);
        this.currentWord = result.word;
        result.word = this.replace(result.word);
        return result;
    }

    private String replace(String s) {
        return s.charAt(0) + "?".repeat(s.length() - 1);
    }

    @Override
    public GuessResult checkGuess(Guess guess) {
        if (guess.guessWord.equals(this.currentWord)) {
            return new GuessResult(true);
        }
        return new GuessResult(false);
    }
}
