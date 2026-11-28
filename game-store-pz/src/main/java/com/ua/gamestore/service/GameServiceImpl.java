package com.ua.gamestore.service;

import com.ua.gamestore.domain.Game;
import com.ua.gamestore.exception.GameNotFoundException;
import com.ua.gamestore.repository.GameRepository;
import com.ua.gamestore.service.mapper.GameMapper;
import com.ua.gamestore.web.dto.GameCardDto;
import com.ua.gamestore.web.dto.GameDetailDto;
import com.ua.gamestore.web.dto.GameRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository repository;
    private final GameMapper mapper;

    public GameServiceImpl(GameRepository repository, GameMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<GameCardDto> findAll() {
        return findAll(null);
    }

    @Override
    public List<GameCardDto> findAll(String genre) {
        return findAll(genre, null);
    }

    @Override
    public List<GameCardDto> findAll(String genre, String platform) {
        List<Game> games = (genre == null || genre.isBlank())
                ? repository.findAll()
                : repository.findByGenre(genre.strip());
        String selectedPlatform = platform == null ? "" : platform.strip();
        return games.stream()
                .filter(game -> selectedPlatform.isEmpty() || selectedPlatform.equalsIgnoreCase(game.platform()))
                .map(mapper::toCard)
                .toList();
    }

    @Override
    public GameDetailDto findById(Long id) {
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        return mapper.toDetail(game);
    }

    @Override
    public GameDetailDto create(GameRequest request) {
        return mapper.toDetail(repository.save(mapper.toDomain(request)));
    }

    @Override
    public GameDetailDto update(Long id, GameRequest request) {
        Game replacement = mapper.toDomain(request);
        replacement.setId(id);
        Game game = repository.replace(replacement)
                .orElseThrow(() -> new GameNotFoundException(id));
        return mapper.toDetail(game);
    }

    @Override
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new GameNotFoundException(id);
        }
    }
}
