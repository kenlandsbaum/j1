package com.example.demo.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @InjectMocks
    private GameController gameController;
    @Mock
    private IGameService gameService;

    static Game testGame;

    @BeforeAll
    static void setGame() {
        GameControllerTest.testGame = new Game(1L, 2L);
    }

    @Test
    void createGame() {
        when(gameService.createGame(GameControllerTest.testGame)).thenReturn(GameControllerTest.testGame);
        ResponseEntity<Game> actual = gameController.createGame(GameControllerTest.testGame);
        assertNotNull(actual.getBody());
        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
    }

    @Test
    void getGame() {
        when(gameService.getGame(1L)).thenReturn(GameControllerTest.testGame);
        when(gameService.getGame(2L)).thenReturn(null);
        ResponseEntity<Game> actual = gameController.getGame(1L);
        assertNotNull(actual.getBody());
        assertEquals(HttpStatus.OK, actual.getStatusCode());

        assertThrows(ResponseStatusException.class, () -> gameController.getGame(2L));
    }
}