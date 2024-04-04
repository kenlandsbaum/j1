package com.example.demo.player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Test
    void getPlayers() {
        List<Player> testPlayers = List.of(
                new Player(1L, "ken", 99),
                new Player(1L, "jen", 100),
                new Player(1L, "sam", 101));
        when(playerRepository.findAll()).thenReturn(testPlayers);
        List<Player> actual = playerService.getPlayers();
        assertEquals(3, actual.size());
        assertEquals("sam", actual.getLast().getName());
    }

    @Test
    void getPlayer() {
        Player testPlayer = new Player(1L, "ken", 99);
        when(playerRepository.findById(1L)).thenReturn(Optional.of(testPlayer));
        when(playerRepository.findById(2L)).thenReturn(Optional.empty());
        Player actual = playerService.getPlayer(1L);
        assertEquals("ken", actual.getName());
        assertEquals(99, actual.getScore());
        assertNull(playerService.getPlayer(2L));
    }

    @Test
    void createPlayer() {
        Player testPlayer = new Player(1L, "ken", 99);
        when(playerRepository.save(testPlayer)).thenReturn(testPlayer);
        Player actual = playerService.createPlayer(testPlayer);
        assertEquals(testPlayer, actual);
    }
}