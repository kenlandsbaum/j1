package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuessTest {

    @Test
    public void smokeTest() {
        Guess g = new Guess();
        g.playerId = 1L;
        g.gameId = 1L;
        g.guessWord = "testing";
        assertEquals("testing", g.guessWord);
    }
}