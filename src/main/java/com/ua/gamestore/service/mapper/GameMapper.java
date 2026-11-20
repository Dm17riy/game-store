package com.ua.gamestore.service.mapper;

import com.ua.gamestore.domain.Game;
import com.ua.gamestore.web.dto.GameCardDto;
import com.ua.gamestore.web.dto.GameDetailDto;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    public GameCardDto toCard(Game game) {
        return new GameCardDto(game.id(), game.title(), game.genre(), game.price());
    }

    public GameDetailDto toDetail(Game game) {
        return new GameDetailDto(game.id(), game.title(), game.studio(), game.genre(),
                game.price(), game.releaseYear(), game.description());
    }
}
