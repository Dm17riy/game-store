package com.ua.gamestore.service;

import com.ua.gamestore.domain.Game;
import com.ua.gamestore.exception.GameNotFoundException;
import com.ua.gamestore.repository.GameRepository;
import com.ua.gamestore.service.mapper.GameMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameServiceImplTest {
    private final GameRepository repository = mock(GameRepository.class);
    private final GameService service = new GameServiceImpl(repository, new GameMapper());

    @Test
    void findByIdReturnsGameDetails() {
        Game game = new Game(1L, "Hades", "Supergiant Games", "Roguelite",
                new BigDecimal("499.00"), 2020, "Escape the Underworld.");
        when(repository.findById(1L)).thenReturn(Optional.of(game));

        var details = service.findById(1L);

        assertThat(details.id()).isEqualTo(1L);
        assertThat(details.title()).isEqualTo("Hades");
        assertThat(details.releaseYear()).isEqualTo(2020);
    }

    @Test
    void findByIdThrowsWhenGameIsMissing() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(999L))
                .isInstanceOf(GameNotFoundException.class);
    }
}
