package com.ua.gamestore.repository;

import com.ua.gamestore.domain.Game;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryGameRepository implements GameRepository {
    private final ConcurrentNavigableMap<Long, Game> games = new ConcurrentSkipListMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public List<Game> findAll() {
        return List.copyOf(games.values());
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
                    game.price(), game.releaseYear(), game.description());
        } else {
            sequence.accumulateAndGet(game.id(), Math::max);
        }
        games.put(game.id(), game);
        return game;
    }
}
