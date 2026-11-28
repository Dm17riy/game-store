package com.ua.gamestore.repository;

import com.ua.gamestore.domain.Game;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryGameRepository implements GameRepository {
    private final Map<Long, Game> games = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public List<Game> findAll() {
        return games.values().stream().sorted(Comparator.comparing(Game::id)).toList();
    }

    @Override
    public List<Game> findByGenre(String genre) {
        return findAll().stream()
                .filter(game -> genre.equalsIgnoreCase(game.genre()))
                .toList();
    }

    @Override
    public Optional<Game> findById(Long id) {
        return Optional.ofNullable(games.get(id));
    }

    @Override
    public Game save(Game game) {
        if (game.id() == null) {
            Long id = sequence.incrementAndGet();
            game = new Game(id, game.title(), game.studio(), game.genre(),
                    game.price(), game.releaseYear(), game.description(), game.platform());
        } else {
            sequence.accumulateAndGet(game.id(), Math::max);
        }
        games.put(game.id(), game);
        return game;
    }

    @Override
    public Optional<Game> replace(Game game) {
        return games.replace(game.id(), game) == null ? Optional.empty() : Optional.of(game);
    }

    @Override
    public boolean deleteById(Long id) {
        return games.remove(id) != null;
    }
}
