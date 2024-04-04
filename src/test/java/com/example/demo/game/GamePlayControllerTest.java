package com.example.demo.game;

import com.example.demo.dto.DictionaryApiDTO;
import com.example.demo.dto.Guess;
import com.example.demo.dto.GuessResult;
import com.example.demo.dto.MeaningDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GamePlayControllerTest {

    @InjectMocks
    private GamePlayController controller;
    @Mock
    private IGameService gameService;

    private String expectedString = "{\"word\":\"thing\",\"meanings\":[{\"partOfSpeech\":\"noun\",\"definitions\":[{\"definition\":\"A thing.\"}]}]}";

    private DictionaryApiDTO getDto() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(this.expectedString, DictionaryApiDTO.class);
    }

    @Test
    void startGame() throws JsonProcessingException {
        Game testGame = new Game(1L, 1L);
        DictionaryApiDTO expected = this.getDto();
        when(gameService.startGame(1L)).thenReturn(expected);
        ResponseEntity<DictionaryApiDTO> actual = controller.startGame(testGame);
        assertNotNull(actual.getBody());
        assertEquals(expected.word, actual.getBody().word);
    }

    @Test
    void checkGuess() {
        Guess testGuess = new Guess();
        testGuess.guessWord = "test";
        testGuess.gameId = 1L;
        testGuess.playerId = 1L;
        GuessResult expected = new GuessResult(true);

        when(gameService.checkGuess(testGuess)).thenReturn(expected);

        ResponseEntity<GuessResult> actual = controller.checkGuess(testGuess);

        assertNotNull(actual.getBody());
        assertTrue(actual.getBody().isCorrect);
    }
}