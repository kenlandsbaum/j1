package com.example.demo.player;

import java.util.List;


public interface IPlayerService {
    public List<Player> getPlayers();
    public Player getPlayer(Long id);
    public Player createPlayer(Player player);
}
