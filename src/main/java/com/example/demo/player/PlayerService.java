package com.example.demo.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements IPlayerService {
    @Autowired PlayerRepository playerRepository;
    public List<Player> getPlayers() {
        return this.playerRepository.findAll();
    }

    public Player getPlayer(Long id) {
        Optional<Player> player = this.playerRepository.findById(id);
        return player.orElse(null);
    }

    public Player createPlayer(Player player) {
        return  this.playerRepository.save(player);
    }

}
