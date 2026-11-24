package com.ua.gamestore.web;

import com.ua.gamestore.exception.GameNotFoundException;
import com.ua.gamestore.service.GameService;
import com.ua.gamestore.web.dto.GameCardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GameService service;

    @Test
    void listReturnsGameCards() throws Exception {
        when(service.findAll(null)).thenReturn(List.of(
                new GameCardDto(1L, "Hades", "Roguelite", new BigDecimal("499.00"))));

        mockMvc.perform(get("/api/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Hades"));
    }

    @Test
    void missingGameReturnsStructuredNotFound() throws Exception {
        when(service.findById(999L)).thenThrow(new GameNotFoundException(999L));

        mockMvc.perform(get("/api/games/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Game with id 999 was not found"))
                .andExpect(jsonPath("$.details").isEmpty());
    }

    @Test
    void invalidGameReturnsStructuredBadRequest() throws Exception {
        mockMvc.perform(post("/api/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title":"", "studio":"Studio", "genre":"Adventure",
                                 "price":-20, "releaseYear":2020, "description":"Demo"}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details.length()").value(2));

        verifyNoInteractions(service);
    }
}
