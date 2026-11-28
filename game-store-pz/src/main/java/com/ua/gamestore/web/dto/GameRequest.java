package com.ua.gamestore.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record GameRequest(
        @NotBlank(message = "вкажіть назву") String title,
        @NotBlank(message = "вкажіть студію") String studio,
        String genre,
        @NotNull(message = "вкажіть ціну") @Positive(message = "ціна має бути додатною") BigDecimal price,
        int releaseYear,
        String description) {
}
