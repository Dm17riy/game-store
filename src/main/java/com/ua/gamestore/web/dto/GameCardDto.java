package com.ua.gamestore.web.dto;

import java.math.BigDecimal;

public record GameCardDto(Long id, String title, String genre, BigDecimal price) {
}
