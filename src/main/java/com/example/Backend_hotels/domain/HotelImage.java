package com.example.Backend_hotels.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "hotel_images")
public class HotelImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    public HotelImage() {}

    public HotelImage(String imageUrl, Hotel hotel) {
        this.imageUrl = imageUrl;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}