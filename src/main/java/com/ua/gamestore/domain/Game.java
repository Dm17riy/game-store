package com.ua.gamestore.domain;

import java.math.BigDecimal;

public record Game(Long id, String title, String studio, String genre,
                   BigDecimal price, int releaseYear, String description) {
}
