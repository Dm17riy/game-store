package com.ua.gamestore.web.dto;

import java.math.BigDecimal;

public record GameDetailDto(Long id, String title, String studio, String genre,
                            BigDecimal price, int releaseYear, String description) {
}
