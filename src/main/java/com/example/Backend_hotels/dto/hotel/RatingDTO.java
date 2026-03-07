package com.example.Backend_hotels.dto.hotel;

public class RatingDTO {

    private Double score;
    private Integer reviews;
    private String label;

    public RatingDTO() {}

    public RatingDTO(Double score, Integer reviews, String label) {
        this.score = score;
        this.reviews = reviews;
        this.label = label;
    }

    public Double getScore() { return score; }
    public Integer getReviews() { return reviews; }
    public String getLabel() { return label; }
}