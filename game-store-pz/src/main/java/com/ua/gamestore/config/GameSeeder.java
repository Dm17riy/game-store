package com.ua.gamestore.config;

import com.ua.gamestore.domain.Game;
import com.ua.gamestore.repository.GameRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GameSeeder implements ApplicationRunner {
    private final GameRepository repository;

    public GameSeeder(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) {
        repository.save(new Game(null, "Hollow Knight", "Team Cherry", "Метроїдванія",
                new BigDecimal("299.00"), 2017, "Подорож підземним королівством Галлоунест.", "PC"));
        repository.save(new Game(null, "Hades", "Supergiant Games", "Роглайт",
                new BigDecimal("499.00"), 2020, "Загрей намагається вибратися з підземного світу.", "Nintendo Switch"));
        repository.save(new Game(null, "Celeste", "Matt Makes Games", "Платформер",
                new BigDecimal("349.00"), 2018, "Медлін долає перешкоди на шляху до вершини гори.", "PC"));
        repository.save(new Game(null, "Stardew Valley", "ConcernedApe", "Симулятор ферми",
                new BigDecimal("279.00"), 2016, "Гравець відновлює ферму й знайомиться з мешканцями долини.", "Android"));
        repository.save(new Game(null, "Outer Wilds", "Mobius Digital", "Пригоди",
                new BigDecimal("549.00"), 2019, "Дослідження планет сонячної системи в часовій петлі.", "PC"));
    }
}
