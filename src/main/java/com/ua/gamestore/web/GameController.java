package com.ua.gamestore.web;

import com.ua.gamestore.service.GameService;
import com.ua.gamestore.web.dto.GameCardDto;
import com.ua.gamestore.web.dto.GameDetailDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping
    public List<GameCardDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public GameDetailDto findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
}
