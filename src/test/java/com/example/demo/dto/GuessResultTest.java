package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuessResultTest {

    @Test
    public void smokeTest() {
        GuessResult gr = new GuessResult(true);
        assertTrue(gr.isCorrect);

        gr = new GuessResult(false);
        assertFalse(gr.isCorrect);
    }
}