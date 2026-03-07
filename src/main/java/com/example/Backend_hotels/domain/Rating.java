package com.example.Backend_hotels.domain;


import jakarta.persistence.Embeddable;

@Embeddable
public class Rating {

    private Double score;
    private Integer reviews;
    private String label;

    public Rating() {}

    public Rating(Double score, Integer reviews, String label) {
        this.score = score;
        this.reviews = reviews;
        this.label = label;
    }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public Integer getReviews() { return reviews; }
    public void setReviews(Integer reviews) { this.reviews = reviews; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
}
