package com.example.demo.player;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private static Player player;
    @BeforeAll
    public static void getPlayer() {
        player = new Player();
        player.setId(1L);
        player.setName("ken");
        player.setScore(42);
    }

    @Test
    void setId() {
        Long newId = 2L;
        player.setId(newId);
        assertEquals(newId, player.getId());
    }

    @Test
    void setName() {
        String expected = "jen";
        player.setName(expected);
        assertEquals(expected, player.getName());
    }



    @Test
    void setScore() {
        int expected = 99;
        player.setScore(expected);
        assertEquals(expected, player.getScore());
    }
}