package com.example.demo.game;

import jakarta.persistence.*;

@Entity
@Table
public class Game {
    @Id
    @SequenceGenerator(name="game_sequence", sequenceName = "game_sequence", allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    private Long id;

    public Game() {}
    public Game(Long id, Long playerId) {
        this.id = id;
        this.playerId = playerId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    private Long playerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
