package com.example.demo.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    private GameService gameService;
    @Mock
    private GameRepository gameRepository;

    static Game testGame;

    @BeforeAll
    static void setGame() {
        Long gameId = 1L;
        Long playerId = 2L;
        GameServiceTest.testGame = new Game(gameId, playerId);
    }

    @Test
    void createGame() {
        when(gameRepository.save(GameServiceTest.testGame)).thenReturn(GameServiceTest.testGame);
        Game actual = gameService.createGame(testGame);
        assertEquals(1L, actual.getId());
        assertEquals(2L, actual.getPlayerId());
    }

    @Test
    void getGame() {
        when(gameRepository.findById(1L)).thenReturn(Optional.ofNullable(GameServiceTest.testGame));
        when(gameRepository.findById(2L)).thenReturn(Optional.empty());

        Game actual = gameService.getGame(1L);
        assertEquals(GameServiceTest.testGame.getPlayerId(), actual.getPlayerId());

        actual = gameService.getGame(2L);
        assertNull(actual);
    }
}