package com.example.Backend_hotels.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer roomNumber;
    private String type;
    private Integer beds;
    private Integer capacity;
    private Double price;
    private Boolean availability;

    // 🔥 Relação com Hotel
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    // 🔥 Relação com imagens da Room
    @JsonManagedReference
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomImage> images = new ArrayList<>();

    public Room() {}

    // ========================
    // Getters e Setters
    // ========================

    public Long getId() {
        return id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<RoomImage> getImages() {
        return images;
    }

    public void setImages(List<RoomImage> images) {
        this.images = images;
    }

    // 🔥 Método auxiliar profissional
    public void addImage(RoomImage image) {
        images.add(image);
        image.setRoom(this);
    }

    public void removeImage(RoomImage image) {
        images.remove(image);
        image.setRoom(null);
    }
}
