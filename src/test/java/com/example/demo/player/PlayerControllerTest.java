package com.example.demo.player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @InjectMocks
    private PlayerController playerController;
    @Mock
    private PlayerService playerService;

    @Test
    void getPlayers() {
        List<Player> testPlayers = List.of(
                new Player(1L, "ken", 99),
                new Player(1L, "jen", 100),
                new Player(1L, "sam", 101));
        when(playerService.getPlayers()).thenReturn(testPlayers);
        ResponseEntity<List<Player>> actual = playerController.getPlayers();
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(testPlayers, actual.getBody());
    }

    @Test
    void getPlayer() {
        Player testPlayer = new Player(1L, "ken", 99);
        when(playerService.getPlayer(1L)).thenReturn(testPlayer);
        when(playerService.getPlayer(2L)).thenReturn(null);
        ResponseEntity<Player> actual = playerController.getPlayer(1L);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(testPlayer, actual.getBody());

        assertThrows(ResponseStatusException.class, () ->playerController.getPlayer(2L));
    }

    @Test
    void postPlayer() {
        Player testPlayer1 = new Player(1L, "ken", 99);
        Player testPlayer2 = new Player(2L, "jen", 99);
        when(playerService.createPlayer(testPlayer1)).thenReturn(testPlayer1);
        when(playerService.createPlayer(testPlayer2)).thenThrow(RuntimeException.class);

        ResponseEntity<Player> actual = playerController.postPlayer(testPlayer1);
        assertNotNull(actual.getBody());
        assertEquals(testPlayer1.getName(), actual.getBody().getName());

        assertThrows(ResponseStatusException.class, () ->playerController.postPlayer(testPlayer2));
    }
}