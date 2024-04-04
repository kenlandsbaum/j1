package com.example.demo.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void getId() {
        Game testGame = new Game();
        testGame.setId(1L);
        assertEquals(1L, testGame.getId());
    }

    @Test
    void getPlayerId() {
        Game testGame = new Game(1L, 2L);
        assertEquals(2L, testGame.getPlayerId());
    }
}