package com.example.demo.dto;

public class GameDTO {
    public Long gameId;
    public Long playerId;

    public GameDTO() {}
    public GameDTO(Long gameId, Long playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
    }
}
