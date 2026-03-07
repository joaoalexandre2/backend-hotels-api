package com.example.Backend_hotels.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "room_images")
public class RoomImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    public RoomImage() {}

    public RoomImage(String imageUrl, Room room) {
        this.imageUrl = imageUrl;
        this.room = room;
    }


    // ========================
    // Getters e Setters
    // ========================

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Room getRoom() {
        return room;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
