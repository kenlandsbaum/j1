package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameDTOTest {

    @Test
    public void smokeTest() {
        GameDTO g = new GameDTO();
        g.gameId = 1L;
        g.playerId = 2L;
        assertEquals(1L, g.gameId);
        assertEquals(2L, g.playerId);
    }
}