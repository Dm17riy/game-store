package com.ua.gamestore.domain;

import java.math.BigDecimal;

public class Game {
    private Long id;
    private String title;
    private String studio;
    private String genre;
    private BigDecimal price;
    private int releaseYear;
    private String description;
    private String platform;

    public Game(Long id, String title, String studio, String genre,
                BigDecimal price, int releaseYear, String description) {
        this(id, title, studio, genre, price, releaseYear, description, null);
    }

    public Game(Long id, String title, String studio, String genre,
                BigDecimal price, int releaseYear, String description, String platform) {
        this.id = id;
        this.title = title;
        this.studio = studio;
        this.genre = genre;
        this.price = price;
        this.releaseYear = releaseYear;
        this.description = description;
        this.platform = platform;
    }

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String title() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String studio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String genre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BigDecimal price() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int releaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String platform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
